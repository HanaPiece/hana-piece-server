package com.project.hana_piece.account.service;

import static com.project.hana_piece.account.domain.AccountType.*;
import static com.project.hana_piece.account.domain.AccountType.isParkingAccountType;
import static com.project.hana_piece.account.util.AccountNumberGenerator.generateAccountNumber;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountTransaction;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.dto.AccountDailyTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountGetResponse;
import com.project.hana_piece.account.dto.AccountMonthTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountTypeRegRequest;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.dto.UserGoalAccountGetResponse;
import com.project.hana_piece.account.exception.AccountInvalidException;
import com.project.hana_piece.account.exception.AccountNotFoundException;
import com.project.hana_piece.account.projection.UserGoalAccountSummary;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.account.repository.AccountTransactionRepository;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserInvalidException;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;
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
        // 등록할 계좌
        Optional<Account> salaryAccount = accountRepository.findById(request.salaryAccountId());
        Optional<Account> savingAccount = accountRepository.findById(request.savingAccountId());
        Optional<Account> lifeAccount = accountRepository.findById(request.lifeAccountId());
        Optional<Account> spareAccount = accountRepository.findById(request.spareAccountId());

        // 사용자의 기존 등록된 계좌
        List<Account> beforeAccountList = accountRepository.findCheckingAccount(userId);

        // 기존 등록된 계좌들 계좌 타입 초기화
        beforeAccountList.forEach(account -> {
            if(isParkingAccountType(account.getAccountTypeCd())) {
                account.setAccountTypeCd(CHECKING);
            }
        });

        salaryAccount.ifPresent(account -> registerSpecificAccountType(userId, account, SALARY));
        savingAccount.ifPresent(account -> registerSpecificAccountType(userId, account, SAVING));
        lifeAccount.ifPresent(account -> registerSpecificAccountType(userId, account, LIFE));
        spareAccount.ifPresent(account -> registerSpecificAccountType(userId, account, SPARE));
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

    public List<AccountGetResponse> findSavingAccountList(Long userId){
        List<Account> accountList = accountRepository.findSavingAccount(userId);
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

    public AccountMonthTransactionGetResponse findAccountMonthTransactionList(Long userId, Long accountId, Integer transactionMonth) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new AccountNotFoundException(accountId));
        List<AccountTransaction> dailyTransactionProjectionList = accountTransactionRepository.findDailyTransactionList(
            accountId, transactionMonth);
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
        Long monthlySum = dailyTransactionList.stream()
            .mapToLong(transaction -> Math.abs(transaction.amount())).sum();

        // 월 별 거래 타입 별 통계
        Map<String, Long> amountByType = dailyTransactionList.stream()
            .collect(Collectors.groupingBy(
                AccountDailyTransactionGetResponse::accountTransactionType,
                Collectors.summingLong((transaction -> Math.abs(transaction.amount()))
            )));

        return new AccountMonthTransactionGetResponse(monthlySum, amountByType, amountByDay, dailyTransactionList);
    }
}
