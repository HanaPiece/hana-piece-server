package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.GoalType;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.dto.UserGoalDetailGetResponse;
import com.project.hana_piece.goal.dto.UserGoalGetResponse;
import com.project.hana_piece.goal.dto.UserGoalTypeGetResponse;
import com.project.hana_piece.goal.dto.UserGoalUpsertRequest;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.repository.ApartmentRepository;
import com.project.hana_piece.goal.repository.CarRepository;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.goal.repository.WishRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserNotFoundException;
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
    private final ApartmentRepository apartmentRepository;
    private final CarRepository carRepository;
    private final WishRepository wishRepository;

    public List<UserGoalGetResponse> findUserGoalsByUserId(Long userId) {
        List<UserGoal> userGoals = userGoalRepository.findByUserUserId(userId);

        return userGoals.stream()
                .map(UserGoalGetResponse::fromEntity)
                .toList();
    }

    public UserGoalGetResponse findUserGoalById(Long userGoalId) {
        UserGoal userGoal = userGoalRepository.findById(userGoalId)
                .orElseThrow(() -> new UserGoalNotFoundException(userGoalId));
        return UserGoalGetResponse.fromEntity(userGoal);
    }

    public List<UserGoalTypeGetResponse> findUserGoalTypesByUserId(Long userId) {
        List<UserGoal> userGoals = userGoalRepository.findByUserUserId(userId);
        return userGoals.stream()
                .map(UserGoalTypeGetResponse::fromEntity)
                .distinct()
                .toList();
    }

    @Transactional(readOnly = true)
    public UserGoalDetailGetResponse findUserGoalByIdAndUserId(Long goalUserId, Long userId) {
        UserGoal userGoal = userGoalRepository.findByUserGoalIdAndUserUserId(goalUserId, userId)
                .orElseThrow(() -> new UserGoalNotFoundException(goalUserId));
        return UserGoalDetailGetResponse.fromEntity(userGoal, apartmentRepository, carRepository, wishRepository);
    }

    @Transactional
    public UserGoalGetResponse upsertUserGoal(UserGoalUpsertRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        UserGoal userGoal;
        if (request.userGoalId() != null) {
            UserGoal existingUserGoal = userGoalRepository.findById(request.userGoalId())
                    .orElseThrow(() -> new UserGoalNotFoundException(request.userGoalId()));
            userGoal = updateUserGoal(existingUserGoal, request);
        } else {
            userGoal = createUserGoal(user, request);
        }
        userGoalRepository.save(userGoal);
        return UserGoalGetResponse.fromEntity(userGoal);
    }

    private UserGoal createUserGoal(User user, UserGoalUpsertRequest request) {
        return UserGoal.builder()
                .user(user)
                .goalAlias(request.goalAlias())
                .goalTypeCd(request.goalTypeCd())
                .goalSpecificId(request.goalSpecificId())
                .goalBeginDate(request.goalBeginDate())
                .duration(request.duration())
                .amount(request.amount())
                .build();
    }

    private UserGoal updateUserGoal(UserGoal existingUserGoal, UserGoalUpsertRequest request) {
        existingUserGoal.setGoalAlias(request.goalAlias());
        existingUserGoal.setGoalTypeCd(GoalType.valueOf(request.goalTypeCd()));
        existingUserGoal.setGoalSpecificId(request.goalSpecificId());
        existingUserGoal.setGoalBeginDate(request.goalBeginDate());
        existingUserGoal.setDuration(request.duration());
        existingUserGoal.setAmount(request.amount());
        return existingUserGoal;
    }
}