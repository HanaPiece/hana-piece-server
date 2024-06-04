package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.projection.UserGoalSummary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record UserGoalListGetResponse(
        Long userGoalId,
        String goalAlias,
        String goalBeginDate,
        Integer duration,
        Long amount,
        List<String> productNames,
        Long savingMoney
) {
    public static UserGoalListGetResponse fromProjection(UserGoalSummary projection) {
        List<String> productNamesList = projection.getProductNames() != null ?
                Arrays.asList(projection.getProductNames().split(",")) : Collections.emptyList();

        return new UserGoalListGetResponse(
                projection.getUserGoalId(),
                projection.getGoalAlias(),
                projection.getGoalBeginDate(),
                projection.getDuration(),
                projection.getAmount(),
                productNamesList,
                projection.getSavingMoney()
        );
    }
}

