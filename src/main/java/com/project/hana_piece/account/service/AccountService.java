package com.project.hana_piece.account.service;

import static com.project.hana_piece.account.domain.AccountType.*;
import static com.project.hana_piece.account.domain.AccountType.isParkingAccountType;
import static com.project.hana_piece.account.util.AccountNumberGenerator.generateAccountNumber;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.dto.AccountGetResponse;
import com.project.hana_piece.account.dto.AccountTypeRegRequest;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.exception.AccountInvalidException;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

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
        List<Account> beforeAccountList = accountRepository.findByAccountTypeCdAndUserUserId(userId);

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
        if(account.getUser().getUserId() != userId) throw new AccountInvalidException();

        account.setAccountTypeCd(accountType);
    }

    public List<AccountGetResponse> findCheckingAccountList(Long userId){
        List<Account> accountList = accountRepository.findByAccountTypeCdAndUserUserId(userId);
        return accountList.stream().map(AccountGetResponse::fromEntity).toList();
    }
}
