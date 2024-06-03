package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.*;
import com.project.hana_piece.goal.repository.ApartmentRepository;
import com.project.hana_piece.goal.repository.CarRepository;
import com.project.hana_piece.goal.repository.WishRepository;

public record UserGoalDetailGetResponse(
        String goalAlias,
        String goalTypeCd,
        Long goalSpecificId,
        GoalDetail detail
) {
    public interface GoalDetail {}

    public record ApartmentDetail(String apartmentNm, Long apartmentPrice, String regionNm, Long exclusiveArea) implements GoalDetail {}

    public record CarDetail(String carNm, Long carPrice) implements GoalDetail {}

    public record WishDetail(String wishNm, Long wishPrice) implements GoalDetail {}

    public static UserGoalDetailGetResponse fromEntity(UserGoal userGoal, ApartmentRepository apartmentRepository, CarRepository carRepository, WishRepository wishRepository) {
        UserGoalDetailGetResponse.GoalDetail detail = null;

        switch (userGoal.getGoalTypeCd()) {
            case "HOUSE":
                Apartment apartment = apartmentRepository.findById(userGoal.getGoalSpecificId()).orElse(null);
                if (apartment != null) {
                    detail = new ApartmentDetail(apartment.getApartmentNm(), apartment.getApartmentPrice(), apartment.getRegionNm(), apartment.getExclusiveArea());
                }
                break;
            case "CAR":
                Car car = carRepository.findById(userGoal.getGoalSpecificId()).orElse(null);
                if (car != null) {
                    detail = new CarDetail(car.getCarNm(), car.getCarPrice());
                }
                break;
            case "WISH":
            default:
                Wish wish = wishRepository.findById(userGoal.getGoalSpecificId()).orElse(null);
                if (wish != null) {
                    detail = new WishDetail(wish.getWishNm(), wish.getWishPrice());
                }
                break;
        }

        return new UserGoalDetailGetResponse(userGoal.getGoalAlias(), userGoal.getGoalTypeCd(), userGoal.getGoalSpecificId(), detail);
    }
}