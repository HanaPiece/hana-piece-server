package com.project.hana_piece.goal.service;

import com.project.hana_piece.goal.domain.GoalType;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.domain.Wish;
import com.project.hana_piece.goal.dto.*;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.goal.projection.UserGoalSummary;
import com.project.hana_piece.goal.repository.ApartmentRepository;
import com.project.hana_piece.goal.repository.CarRepository;
import com.project.hana_piece.goal.repository.UserGoalRepository;
import com.project.hana_piece.goal.repository.WishRepository;
import com.project.hana_piece.product.domain.Product;
import com.project.hana_piece.product.exception.ProductNotFoundException;
import com.project.hana_piece.product.repository.ProductRepository;
import com.project.hana_piece.user.domain.User;
import com.project.hana_piece.user.exception.UserNotFoundException;
import com.project.hana_piece.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class UserGoalService {


    private final UserRepository userRepository;
    private final ProductRepository productRepository;
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

    public UserGoalDetailGetResponse findUserGoalByIdAndUserId(Long goalUserId, Long userId) {
        UserGoal userGoal = userGoalRepository.findByUserGoalIdAndUserUserId(goalUserId, userId)
                .orElseThrow(() -> new UserGoalNotFoundException(goalUserId));
        return UserGoalDetailGetResponse.fromEntity(userGoal, apartmentRepository, carRepository, wishRepository);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserGoalGetResponse upsertUserGoal(UserGoalUpsertRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        UserGoal userGoal;
        Long goalSpecificId = request.goalSpecificId();

        if (GoalType.WISH.getProperty().equals(request.goalTypeCd())) {
            Wish wish = Wish.builder()
                    .wishNm(request.goalAlias())
                    .wishPrice(request.amount())
                    .build();
            wish = wishRepository.save(wish);
            goalSpecificId = wish.getWishId();
        }

        if (request.userGoalId() != null) {
            UserGoal existingUserGoal = userGoalRepository.findById(request.userGoalId())
                    .orElseThrow(() -> new UserGoalNotFoundException(request.userGoalId()));
            userGoal = updateUserGoal(existingUserGoal, request, goalSpecificId);
        } else {
            userGoal = createUserGoal(user, request, goalSpecificId);
        }
        userGoalRepository.save(userGoal);

        return UserGoalGetResponse.fromEntity(userGoal);
    }

    private UserGoal createUserGoal(User user, UserGoalUpsertRequest request, Long goalSpecificId) {
        return UserGoal.builder()
                .user(user)
                .goalAlias(request.goalAlias())
                .goalTypeCd(request.goalTypeCd())
                .goalSpecificId(goalSpecificId)
                .goalBeginDate(request.goalBeginDate())
                .duration(request.duration())
                .amount(request.amount())
                .build();
    }

    private UserGoal updateUserGoal(UserGoal existingUserGoal, UserGoalUpsertRequest request, Long goalSpecificId) {
        existingUserGoal.setGoalAlias(request.goalAlias());
        existingUserGoal.setGoalTypeCd(GoalType.valueOf(request.goalTypeCd()));
        existingUserGoal.setGoalSpecificId(goalSpecificId);
        existingUserGoal.setGoalBeginDate(request.goalBeginDate());
        existingUserGoal.setDuration(request.duration());
        existingUserGoal.setAmount(request.amount());
        return existingUserGoal;
    }

    public List<UserGoalListGetResponse> findUserGoalList(Long userId) {
        List<UserGoalSummary> userGoalSummaryList = userGoalRepository.findUserGoalList(userId);

        return userGoalSummaryList.stream().map(summary -> {
            List<UserGoalListGetResponse.EnrolledProductResponse> enrolledProducts =
                    summary.getProductIds() == null ?
                            List.of() :
                            Stream.of(summary.getProductIds().split(","))
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty())
                                    .map(Long::parseLong)
                                    .map(id -> new UserGoalListGetResponse.EnrolledProductResponse(id, getProductNameById(id)))
                                    .toList();
            return UserGoalListGetResponse.fromProjection(summary, enrolledProducts);
        }).toList();
    }

    private String getProductNameById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return product.getProductNm();
    }
}
