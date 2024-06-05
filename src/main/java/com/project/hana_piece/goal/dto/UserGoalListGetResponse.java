package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.projection.UserGoalSummary;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public record UserGoalListGetResponse(
        Long userGoalId,
        String goalAlias,
        String goalTypeCd,
        Long goalSpecificId,
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
                projection.getGoalTypeCd(),
                projection.getGoalSpecificId(),
                projection.getGoalBeginDate(),
                projection.getDuration(),
                projection.getAmount(),
                productNamesList,
                projection.getSavingMoney()
        );
    }
}

