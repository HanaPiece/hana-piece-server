package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.UserGoal;

public record UserGoalGetResponse(
        Long userGoalId,
        String goalAlias,
        String goalTypeCd,
        Long goalSpecificId,
        String goalBeginDate,
        Integer duration,
        Long amount
) {
    public static UserGoalGetResponse fromEntity(UserGoal userGoal) {
        return new UserGoalGetResponse(
                userGoal.getUserGoalId(),
                userGoal.getGoalAlias(),
                userGoal.getGoalTypeCd(),
                userGoal.getGoalSpecificId(),
                userGoal.getGoalBeginDate(),
                userGoal.getDuration(),
                userGoal.getAmount()
        );
    }
}
