package com.project.hana_piece.user.service;

import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserGetResponse findByUserId(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException());
        return UserGetResponse.fromEntity(user);
    }
}
