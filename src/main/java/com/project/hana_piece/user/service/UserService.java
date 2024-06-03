package com.project.hana_piece.user.service;

import com.project.hana_piece.common.util.JwtUtil;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.dto.UserLoginRequest;
import com.project.hana_piece.user.dto.UserLoginResponse;
import com.project.hana_piece.user.dto.UserSalaryUpsertResponse;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public UserLoginResponse login(UserLoginRequest dto) {
        User loginUser = userRepository.findByPassword(dto.password()).orElseThrow(UserNotFoundException::new);
        String generatedAccessToken = jwtUtil.generateAccessToken(loginUser.getUserId());

        return new UserLoginResponse(generatedAccessToken, loginUser.getNickname(), loginUser.getSalary());
    }

    public UserGetResponse findByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(userId));
        return UserGetResponse.fromEntity(user);
    }

    @Transactional
    public UserSalaryUpsertResponse upsertUserSalary(Long userId, Long newSalary) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setSalary(newSalary);

        return new UserSalaryUpsertResponse(user.getSalary());
    }
}