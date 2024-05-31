package com.project.hana_piece.goal.dto;

public record UserGoalDetailGetResponse(
        String goalTypeCd,
        Long goalSpecificId,
        GoalDetail detail
) {
    public interface GoalDetail {}

    public record ApartmentDetail(String apartmentNm, Long apartmentPrice, String regionNm, Long exclusiveArea) implements GoalDetail {}

    public record CarDetail(String carNm, Long carPrice) implements GoalDetail {}

    public record WishDetail(String wishNm, Long wishPrice) implements GoalDetail {}
}
