package com.project.hana_piece.account.service;

import static com.project.hana_piece.account.domain.AccountType.*;
import static com.project.hana_piece.account.domain.AccountType.isParkingAccountType;
import static com.project.hana_piece.account.util.AccountNumberGenerator.generateAccountNumber;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountAutoDebit;
import com.project.hana_piece.account.domain.AccountTransaction;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.dto.AccountAutoDebitAdjustGetResponse;
import com.project.hana_piece.account.dto.AccountAutoDebitAdjustUpsertRequest;
import com.project.hana_piece.account.dto.AccountDailyTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountGetResponse;
import com.project.hana_piece.account.dto.AccountMonthTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountSalaryGetResponse;
import com.project.hana_piece.account.dto.AccountSavingGetResponse;
import com.project.hana_piece.account.dto.AccountTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountTypeRegRequest;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.dto.UserGoalAccountGetResponse;
import com.project.hana_piece.account.exception.AccountAutoDebitNotFoundException;
import com.project.hana_piece.account.exception.AccountInvalidException;
import com.project.hana_piece.account.exception.AccountNotFoundException;
import com.project.hana_piece.account.projection.AccountAutoDebitSummary;
import com.project.hana_piece.account.projection.UserGoalAccountSummary;
import com.project.hana_piece.account.repository.AccountAutoDebitRepository;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.account.repository.AccountTransactionRepository;
import com.project.hana_piece.account.repository.AccountTransactionRepositoryCustom;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserInvalidException;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountAutoDebitRepository accountAutoDebitRepository;
    private final AccountTransactionRepository accountTransactionRepository;
    private final AccountTransactionRepositoryCustom accountTransactionRepositoryCustom;
    private final UserRepository userRepository;
    private final UserGoalRepository userGoalRepository;

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
        Account salaryAccount = accountRepository.findById(request.salaryAccountId()).orElseThrow(()->new AccountNotFoundException(request.salaryAccountId()));
        Account savingAccount = accountRepository.findById(request.savingAccountId()).orElseThrow(()->new AccountNotFoundException(request.savingAccountId()));
        Account lifeAccount = accountRepository.findById(request.lifeAccountId()).orElseThrow(()->new AccountNotFoundException(request.lifeAccountId()));
        Account spareAccount = accountRepository.findById(request.spareAccountId()).orElseThrow(()->new AccountNotFoundException(request.spareAccountId()));

        // 사용자의 기존 등록된 계좌
        List<Account> beforeAccountList = accountRepository.findCheckingAccount(userId);

        // 기존 등록된 계좌들 계좌 타입 초기화
        beforeAccountList.forEach(account -> {
            if(!isParkingAccountType(account.getAccountTypeCd()) || !isInstallmentSavingAccountType(account.getAccountTypeCd())) {
                account.setAccountTypeCd(CHECKING);
            }
        });

        // Default 자동이체 생성
        createAutoDebitIfNotExists(salaryAccount, savingAccount, user.getSalaryDay());
        createAutoDebitIfNotExists(salaryAccount, lifeAccount, user.getSalaryDay());
        createAutoDebitIfNotExists(salaryAccount, spareAccount, user.getSalaryDay());

        registerSpecificAccountType(userId, salaryAccount, SALARY);
        registerSpecificAccountType(userId, savingAccount, SAVING);
        registerSpecificAccountType(userId, lifeAccount, LIFE);
        registerSpecificAccountType(userId, spareAccount, SPARE);
    }

    private void createAutoDebitIfNotExists(Account fromAccount, Account toAccount, int debitDay) {
        if (!accountAutoDebitRepository.existsByAccountAccountIdAndTargetAccountId(fromAccount.getAccountId(), toAccount.getAccountId())) {
            AccountAutoDebit accountAutoDebit = AccountAutoDebit.builder()
                .account(fromAccount)
                .targetAccountId(toAccount.getAccountId())
                .autoDebitAmount(0L)
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
        List<AccountTransaction> accountTransactionList = accountTransactionRepository.findByAccountAccountId(accountId);

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
}
