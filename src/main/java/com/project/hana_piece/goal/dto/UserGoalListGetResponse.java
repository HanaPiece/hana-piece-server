package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.projection.UserGoalSummary;

import java.util.List;

public record UserGoalListGetResponse(
        Long userGoalId,
        String goalAlias,
        String goalTypeCd,
        Long goalSpecificId,
        String goalBeginDate,
        Integer duration,
        Long amount,
        List<EnrolledProductResponse> enrolledProducts,
        Long savingMoney
) {
    public static UserGoalListGetResponse fromProjection(UserGoalSummary projection, List<EnrolledProductResponse> enrolledProducts) {
        return new UserGoalListGetResponse(
                projection.getUserGoalId(),
                projection.getGoalAlias(),
                projection.getGoalTypeCd(),
                projection.getGoalSpecificId(),
                projection.getGoalBeginDate(),
                projection.getDuration(),
                projection.getAmount(),
                enrolledProducts,
                projection.getSavingMoney()
        );
    }

    public static record EnrolledProductResponse(
            Long enrolledProductId,
            String enrolledProductName
    ) {
    }
}
