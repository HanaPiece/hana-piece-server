package com.project.hana_piece.user.service;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.domain.AccountAutoDebit;
import com.project.hana_piece.account.repository.AccountRepository;
import com.project.hana_piece.common.util.JwtUtil;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.dto.UserLoginRequest;
import com.project.hana_piece.user.dto.UserLoginResponse;
import com.project.hana_piece.user.dto.UserSalaryGetResponse;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final JwtUtil jwtUtil;


    public UserLoginResponse login(UserLoginRequest dto) {
        User loginUser = userRepository.findByPassword(dto.password()).orElseThrow(()-> new UserNotFoundException());
        String generatedAccessToken = jwtUtil.generateAccessToken(loginUser.getUserId());

        return new UserLoginResponse(generatedAccessToken);
    }

    public UserGetResponse findByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException());
        return UserGetResponse.fromEntity(user);
    }

//    @Transactional(readOnly = true)
//    public UserSalaryGetResponse getUserSalary(Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
//
////        Account account = accountRepository.findByUserIdAndAccountTypeId(userId, 1L)
////                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
//
////        AccountAutoDebit accountAutoDebit = accountAutoDebitRepository.findByAccount(account)
////                .orElseThrow(() -> new IllegalArgumentException("Auto Debit Information not found"));
//
//        return new UserSalaryGetResponse(
//                user.getUserId(),
//                user.getEmail(),
//                user.getSex(),
//                user.getAge(),
//                user.getQualificationTypeCd(),
//                user.getCityType() != null ? user.getCityType().getCityTypeNm() : null,
//                user.getNickname(),
//                user.getSalary(),
//                account.getAccountNumber(),
//                accountAutoDebit.getAutoDebitDay()
//        );
//    }
}
