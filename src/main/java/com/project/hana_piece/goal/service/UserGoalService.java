package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.dto.UserGoalGetResponse;
import com.project.hana_piece.goal.dto.UserGoalTypeGetResponse;
import com.project.hana_piece.goal.dto.UserGoalUpsertRequest;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGoalService {

    private final UserRepository userRepository;
    private final UserGoalRepository userGoalRepository;


    public List<UserGoalGetResponse> findUserGoalsByUserId(Long userId) {
        List<UserGoal> userGoals = userGoalRepository.findByUserUserId(userId);

        return userGoals.stream()
                .map(UserGoalGetResponse::fromEntity)
                .toList();
    }

    public UserGoalGetResponse findUserGoalById(Long userGoalId) {
        UserGoal userGoal = userGoalRepository.findById(userGoalId)
                .orElseThrow(() -> new RuntimeException("User goal not found"));
        return UserGoalGetResponse.fromEntity(userGoal);
    }

    public List<UserGoalTypeGetResponse> findUserGoalTypesByUserId(Long userId) {
        List<UserGoal> userGoals = userGoalRepository.findByUserUserId(userId);
        return userGoals.stream()
                .map(UserGoalTypeGetResponse::fromEntity)
                .distinct()
                .toList();
    }

    @Transactional
    public UserGoalGetResponse upsertUserGoal(UserGoalUpsertRequest request, Long userId) {
        UserGoal userGoal;
        if (request.userGoalId() != null) {
            UserGoal existingUserGoal = userGoalRepository.findById(request.userGoalId())
                    .orElseThrow(() -> new RuntimeException("User goal not found"));
            userGoal = existingUserGoal.builder()
//                    .userId(userId) // keep the same user ID
                    .goalTypeCd(request.goalTypeCd())
                    .goalSpecificId(request.goalSpecificId())
                    .goalBeginDate(request.goalBeginDate())
                    .duration(request.duration())
                    .amount(request.amount())
                    .build();
        } else {
            userGoal = UserGoal.builder()
//                    .userId(userId)
                    .goalTypeCd(request.goalTypeCd())
                    .goalSpecificId(request.goalSpecificId())
                    .goalBeginDate(request.goalBeginDate())
                    .duration(request.duration())
                    .amount(request.amount())
                    .build();
            userGoalRepository.save(userGoal);
        }
        return UserGoalGetResponse.fromEntity(userGoal);
    }
}
