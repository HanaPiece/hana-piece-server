package com.project.hana_piece.account.service;

import static com.project.hana_piece.account.constant.AccountNumberConst.LENGTH_WITHOUT_PREFIX;
import static com.project.hana_piece.account.constant.AccountNumberConst.PREFIX;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountType;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import java.util.Random;
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
    
    public String generateAccountNumber() {
        Random RANDOM = new Random();
        StringBuilder result = new StringBuilder();

        result.append(PREFIX.getProperty());
        for (int i = 0; i < LENGTH_WITHOUT_PREFIX.getProperty(); i++) {
            int digit = RANDOM.nextInt(10);
            result.append(digit);
        }

        return result.toString();
    }
}
