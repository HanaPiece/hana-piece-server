package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.UserGoal;

public record UserGoalGetResponse(
        Long userGoalId,
        Long userId,
        String goalAlias,
        String goalTypeCd,
        Long goalSpecificId,
        String goalBeginDate,
        Integer duration,
        Long amount
) {
    public static UserGoalGetResponse fromEntity(UserGoal userGoal) {
        Long userId = userGoal.getUser() != null ? userGoal.getUser().getUserId() : null;

        return new UserGoalGetResponse(
                userGoal.getUserGoalId(),
                userId,
                userGoal.getGoalAlias(),
                userGoal.getGoalTypeCd(),
                userGoal.getGoalSpecificId(),
                userGoal.getGoalBeginDate(),
                userGoal.getDuration(),
                userGoal.getAmount()
        );
    }
}
