package com.project.hana_piece.account.service;

import static com.project.hana_piece.account.constant.AccountNumberGenerator.generateAccountNumber;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountUpsertResponse saveAccount(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));

        Account savedAccount = accountRepository.save(
            Account.builder().user(user).accountNumber(generateAccountNumber()).accountType(AccountType.CHECKING).balance(0L).build());

        return AccountUpsertResponse.fromEntity(savedAccount);
    }
}
