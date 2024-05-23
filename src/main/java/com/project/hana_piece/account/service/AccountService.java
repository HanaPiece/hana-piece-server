package com.project.hana_piece.account.service;

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

    // TODO 계좌 번호 생성 전략 고민
    public String generateAccountNumber() {
        Random RANDOM = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 14; i++) {
            int digit = RANDOM.nextInt(10); // 0 to 9
            result.append(digit);
        }
        return result.toString();
    }
}
