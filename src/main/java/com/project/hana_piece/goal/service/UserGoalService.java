package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.dto.UserGoalGetResponse;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserRepository userRepository;
    private final UserGoalRepository userGoalRepository;


    public List<UserGoalGetResponse> findUserGoalsByUserId(Long userId) {
        List<UserGoal> userGoals = userGoalRepository.findByUserId(userId);

        return userGoals.stream()
                .map(UserGoalGetResponse::fromEntity)
                .toList();
    }
}
