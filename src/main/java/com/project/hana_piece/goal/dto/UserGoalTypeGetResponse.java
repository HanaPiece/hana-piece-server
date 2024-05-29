package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.UserGoal;

public record UserGoalTypeGetResponse(
        String goalTypeCd
) {
    public static UserGoalTypeGetResponse fromEntity(UserGoal userGoal) {
        return new UserGoalTypeGetResponse(
                userGoal.getGoalTypeCd()
        );
    }
}