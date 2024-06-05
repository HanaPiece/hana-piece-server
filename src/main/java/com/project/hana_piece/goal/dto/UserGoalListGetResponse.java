package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.projection.UserGoalSummary;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
                Stream.of(projection.getProductNames().split(","))
                        .map(String::trim)  // 공백 제거
                        .filter(s -> !s.isEmpty())  // 빈 문자열 제거
                        .toList() : Collections.emptyList();

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

