package com.project.hana_piece.account.service;

import com.google.gson.JsonObject;
import com.project.hana_piece.account.domain.*;
import com.project.hana_piece.account.dto.*;
import com.project.hana_piece.account.exception.AccountAutoDebitNotFoundException;
import com.project.hana_piece.account.exception.AccountInvalidException;
import com.project.hana_piece.account.exception.AccountNotFoundException;
import com.project.hana_piece.account.projection.AccountAutoDebitSummary;
import com.project.hana_piece.account.projection.UserGoalAccountSummary;
import com.project.hana_piece.account.repository.AccountAutoDebitRepository;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.account.repository.AccountTransactionRepository;
import com.project.hana_piece.account.repository.AccountTransactionRepositoryCustom;
import com.project.hana_piece.common.exception.ValueInvalidException;
import com.project.hana_piece.common.util.JsonUtil;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserInvalidException;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.project.hana_piece.account.domain.AccountType.*;
import static com.project.hana_piece.account.util.AccountNumberGenerator.generateAccountNumber;

@Service
@RequiredArgsConstructor
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final AccountAutoDebitRepository accountAutoDebitRepository;
    private final AccountTransactionRepository accountTransactionRepository;
    private final AccountTransactionRepositoryCustom accountTransactionRepositoryCustom;
    private final UserRepository userRepository;
    private final UserGoalRepository userGoalRepository;

    private final JsonUtil jsonUtil;

    public AccountUpsertResponse saveAccount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Account savedAccount = accountRepository.save(
                Account.builder().user(user).accountNumber(generateAccountNumber()).accountType(CHECKING).balance(0L).build());

        return AccountUpsertResponse.fromEntity(savedAccount);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void registerAccountType(Long userId, AccountTypeRegRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        // 등록할 계좌
        Account salaryAccount = accountRepository.findById(request.salaryAccountId()).orElseThrow(() -> new AccountNotFoundException(request.salaryAccountId()));
        Account savingAccount = accountRepository.findById(request.savingAccountId()).orElseThrow(() -> new AccountNotFoundException(request.savingAccountId()));
        Account lifeAccount = accountRepository.findById(request.lifeAccountId()).orElseThrow(() -> new AccountNotFoundException(request.lifeAccountId()));
        Account spareAccount = accountRepository.findById(request.spareAccountId()).orElseThrow(() -> new AccountNotFoundException(request.spareAccountId()));

        // 사용자의 기존 등록된 계좌
        List<Account> beforeAccountList = accountRepository.findCheckingAccount(userId);

        // 각 account_type_cd에 해당하는 account_id와 자동이체 정보를 매핑
        Map<String, Long> autoDebitMap = new HashMap<>();
        Set<String> autoDebitAccountTypes = new HashSet<>(Arrays.asList("LIFE", "SAVING", "SPARE"));

        for (Account account : beforeAccountList) {
            if (autoDebitAccountTypes.contains(account.getAccountTypeCd())) {
                AccountAutoDebit accountAutoDebit = accountAutoDebitRepository.findByTargetAccountId(account.getAccountId())
                        .orElseThrow(() -> new AccountAutoDebitNotFoundException());
                autoDebitMap.put(account.getAccountTypeCd(), accountAutoDebit.getAutoDebitAmount());
            }
        }

        // 기존 등록된 계좌들 계좌 타입 초기화
        beforeAccountList.forEach(account -> {
            if (!isParkingAccountType(account.getAccountTypeCd()) || !isInstallmentSavingAccountType(account.getAccountTypeCd())) {
                account.setAccountTypeCd(CHECKING);
            }
        });

        // 자동이체 정보 수정 또는 생성
        upsertAutoDebit(salaryAccount, lifeAccount, autoDebitMap.get("LIFE"), user.getSalaryDay());
        upsertAutoDebit(salaryAccount, savingAccount, autoDebitMap.get("SAVING"), user.getSalaryDay());
        upsertAutoDebit(salaryAccount, spareAccount, autoDebitMap.get("SPARE"), user.getSalaryDay());

        registerSpecificAccountType(userId, salaryAccount, SALARY);
        registerSpecificAccountType(userId, savingAccount, SAVING);
        registerSpecificAccountType(userId, lifeAccount, LIFE);
        registerSpecificAccountType(userId, spareAccount, SPARE);
    }

    private void upsertAutoDebit(Account fromAccount, Account toAccount, Long debitAmount, int debitDay) {
        Optional<AccountAutoDebit> existingAutoDebitOpt = accountAutoDebitRepository.findByAccount_AccountIdAndTargetAccountId(fromAccount.getAccountId(), toAccount.getAccountId());

        if (existingAutoDebitOpt.isPresent()) {
            AccountAutoDebit existingAutoDebit = existingAutoDebitOpt.get();
            existingAutoDebit.setAutoDebitAmount(debitAmount);
            existingAutoDebit.setAutoDebitDay(debitDay);
            accountAutoDebitRepository.save(existingAutoDebit);
        } else {
            AccountAutoDebit accountAutoDebit = AccountAutoDebit.builder()
                    .account(fromAccount)
                    .targetAccountId(toAccount.getAccountId())
                    .autoDebitAmount(debitAmount != null ? debitAmount : 0L)
                    .autoDebitDay(debitDay)
                    .build();
            accountAutoDebitRepository.save(accountAutoDebit);
        }
    }

    public void registerSpecificAccountType(Long userId, Account account, AccountType accountType) {
        // 계좌 userId 검증
        if(account.getUser() == null || !account.getUser().getUserId().equals(userId)) throw new UserInvalidException(userId);

        account.setAccountTypeCd(accountType);
    }

    public List<AccountGetResponse> findCheckingAccountList(Long userId){
        List<Account> accountList = accountRepository.findCheckingAccount(userId);
        return accountList.stream().map(AccountGetResponse::fromEntity).toList();
    }

    public List<AccountGetResponse> findInstallmentSavingAccountList(Long userId){
        List<Account> accountList = accountRepository.findInstallmentSavingAccount(userId);
        return accountList.stream().map(AccountGetResponse::fromEntity).toList();
    }

    public List<UserGoalAccountGetResponse> findUserGoalAccountList(Long userId, Long userGoalId) {
        UserGoal userGoal = userGoalRepository.findById(userGoalId)
                .orElseThrow(() -> new UserGoalNotFoundException(userGoalId));

        if(userGoal.getUser() == null || !userGoal.getUser().getUserId().equals(userId) ){
            throw new UserInvalidException(userId);
        }

        List<UserGoalAccountSummary> accountList = accountRepository.findUserGoalAccountList(userGoalId);
        return accountList.stream().map(UserGoalAccountGetResponse::fromProjection).toList();
    }

    public List<AccountTransactionGetResponse> findGoalAccountTransactionList(Long userId, Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        List<AccountTransaction> accountTransactionList = accountTransactionRepository.findByAccountAccountIdOrderByCreatedAtDesc(accountId);

        // 적금 통장이 아닐 경우 예외 처리
        if(account.getAccountTypeCd() == INSTALLMENT_SAVING.getProperty()){
            throw new AccountInvalidException(accountId);
        }

        // 사용자 검증
        if(account.getUser() == null || !account.getUser().getUserId().equals(userId) ){
            throw new UserInvalidException(userId);
        }

        return accountTransactionList.stream().map(AccountTransactionGetResponse::fromEntity).toList();
    }

    public AccountMonthTransactionGetResponse findAccountMonthTransactionList(Long userId, Long accountId, Integer transactionYearMonth) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException(accountId));
        List<AccountTransaction> dailyTransactionProjectionList = accountTransactionRepositoryCustom.findDailyTransactionList(
                accountId, transactionYearMonth);
        Long autoDebitTotalAmount = accountTransactionRepositoryCustom.findSumAutoDebitAmount(
                accountId, transactionYearMonth);
        // Projection List -> DTO List
        List<AccountDailyTransactionGetResponse> dailyTransactionList = dailyTransactionProjectionList.stream()
                .map(AccountDailyTransactionGetResponse::fromEntity).toList();

        // 사용자 검증
        if(account.getUser() == null || !account.getUser().getUserId().equals(userId) ){
            throw new UserInvalidException(userId);
        }

        // 일 별 통계
        Map<Integer, Long> amountByDay = dailyTransactionList.stream()
                .collect(Collectors.groupingBy(
                        AccountDailyTransactionGetResponse::transactionDay,
                        Collectors.summingLong((transaction -> Math.abs(transaction.amount()))
                        )));

        // 월 별 통계
        Long monthlyTotalSpending = dailyTransactionList.stream()
                .mapToLong(transaction -> Math.abs(transaction.amount())).sum();

        // 월 별 거래 타입 별 통계
        Map<String, Long> amountByType = dailyTransactionList.stream()
                .collect(Collectors.groupingBy(
                        AccountDailyTransactionGetResponse::accountTransactionType,
                        Collectors.summingLong((transaction -> Math.abs(transaction.amount()))
                        )));

        return new AccountMonthTransactionGetResponse(autoDebitTotalAmount, monthlyTotalSpending, amountByType, amountByDay, dailyTransactionList);
    }

    public AccountSalaryGetResponse findSalaryAccount(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
        Account salaryAccount = accountRepository.findSalaryAccount(userId).orElseThrow(()-> new AccountInvalidException());

        // 사용자 검증
        if(salaryAccount.getUser() == null || !salaryAccount.getUser().getUserId().equals(userId) ){
            throw new UserInvalidException(userId);
        }

        return new AccountSalaryGetResponse(salaryAccount.getAccountId(), salaryAccount.getAccountNumber(), user.getSalary(), user.getSalaryDay());
    }

    public AccountSavingGetResponse findSavingAccount(Long userId) {
        Account savingAccount = accountRepository.findSavingAccount(userId).orElseThrow(()-> new AccountInvalidException());

        // 사용자 검증
        if(savingAccount.getUser() == null || !savingAccount.getUser().getUserId().equals(userId) ){
            throw new UserInvalidException(userId);
        }

        return new AccountSavingGetResponse(savingAccount.getAccountId(), savingAccount.getAccountNumber());
    }

    public List<AccountAutoDebitAdjustGetResponse> findAccountAutoDebitAdjust(Long userId) {
        List<AccountAutoDebitSummary> autoDebitAccountList = accountRepository.findAutoDebitAccount(userId);
        return autoDebitAccountList.stream().map(AccountAutoDebitAdjustGetResponse::fromProjection).toList();
    }

    public AccountAutoDebitSuggestGetResponse findAccountAutoDebitSuggest(Long userId, String type){
        WebClient webClient = WebClient.builder()
                .baseUrl("http://54.180.220.88:5000")
                .build();

        String endpoint = "";
        if(type.equals("init")){
            endpoint = "/first_calc/"+userId;
        } else if(type.equals("lux")){
            endpoint = "/second_calc/"+userId+"/lux";
        } else if(type.equals("save")){
            endpoint = "/second_calc/"+userId+"/save";
        }

        String responseBody = webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonObject jsonObject = jsonUtil.toJson(responseBody);
        JsonObject resultData = jsonUtil.extractProperty(jsonObject, "result_data", JsonObject.class);
        int life = jsonUtil.extractProperty(resultData, "life", Integer.class);
        int reserve = jsonUtil.extractProperty(resultData, "reserve", Integer.class);
        int saving = jsonUtil.extractProperty(resultData, "saving", Integer.class);

        return new AccountAutoDebitSuggestGetResponse(life, reserve, saving);
    }

    public void updateAccountAutoDebitAdjust(AccountAutoDebitAdjustUpsertRequest request) {
        Long savingId = request.savingAccountAutoDebitId();
        Long lifeId = request.lifeAccountAutoDebitId();
        Long spareId = request.spareAccountAutoDebitId();
        AccountAutoDebit savingAccountAutoDebit = accountAutoDebitRepository.findById(savingId).orElseThrow(() -> new AccountAutoDebitNotFoundException(savingId));
        AccountAutoDebit lifeAccountAutoDebit = accountAutoDebitRepository.findById(lifeId).orElseThrow(() -> new AccountAutoDebitNotFoundException(lifeId));
        AccountAutoDebit spareAccountAutoDebit = accountAutoDebitRepository.findById(spareId).orElseThrow(() -> new AccountAutoDebitNotFoundException(spareId));

        savingAccountAutoDebit.setAutoDebitAmount(request.savingAutoDebitAmount());
        lifeAccountAutoDebit.setAutoDebitAmount(request.lifeAutoDebitAmount());
        spareAccountAutoDebit.setAutoDebitAmount(request.spareAutoDebitAmount());
    }

    // TODO 현재 로직은 매우 위험함 각각의 처리에 Transaction 기반으로 동작해야함 추후 필수 수정 사항!!
    @Transactional
    public void executeTodayAccountAutoDebit() {
        Integer day = LocalDateTime.now().getDayOfMonth();
        List<AccountAutoDebit> autoDebitList = accountAutoDebitRepository.findByAutoDebitDay(day);

        autoDebitList.stream().forEach(accountAutoDebit -> {
            try {
                Account senderAccount = accountAutoDebit.getAccount();
                Account recieverAccount = accountRepository.findById(
                                accountAutoDebit.getTargetAccountId())
                        .orElseThrow(AccountNotFoundException::new);
                AccountTransaction senderAccountTransaction = AccountTransaction.builder()
                        .accountTransactionTypeCd(AccountTransactionType.TRANSFER.getProperty())
                        .accountPaymentTypeCd(AccountPaymentType.TRANSFER.getProperty())
                        .account(senderAccount)
                        .targetNm(recieverAccount.getAccountId().toString())
                        .oldBalance(senderAccount.getBalance())
                        .newBalance(senderAccount.getBalance() - accountAutoDebit.getAutoDebitAmount())
                        .amount(-accountAutoDebit.getAutoDebitAmount())
                        .build();
                AccountTransaction recieverAccountTransaction = AccountTransaction.builder()
                        .accountTransactionTypeCd(AccountTransactionType.TRANSFER.getProperty())
                        .accountPaymentTypeCd(AccountPaymentType.TRANSFER.getProperty())
                        .account(recieverAccount)
                        .targetNm(senderAccount.getAccountId().toString())
                        .oldBalance(recieverAccount.getBalance())
                        .newBalance(
                                recieverAccount.getBalance() + accountAutoDebit.getAutoDebitAmount())
                        .amount(+accountAutoDebit.getAutoDebitAmount())
                        .build();

                // 계좌 잔액 수정
                senderAccount.minusAmount(accountAutoDebit.getAutoDebitAmount());
                recieverAccount.plusAmount(accountAutoDebit.getAutoDebitAmount());

                // 계좌 이체 정보 저장
                accountTransactionRepository.save(senderAccountTransaction);
                accountTransactionRepository.save(recieverAccountTransaction);
            }catch (ValueInvalidException vie) {
                //TODO 잔액 부족 시 무시 후, 반복문 계속 진행, 추후 미지급 금액에 대한 처리 필요
                logger.info(vie.getMessage());

            }
        });
    }
}
