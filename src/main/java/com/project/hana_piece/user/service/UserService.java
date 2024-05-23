package com.project.hana_piece.user.service;

import com.project.hana_piece.common.util.JwtUtil;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.dto.UserLoginRequest;
import com.project.hana_piece.user.dto.UserLoginResponse;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
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
}
