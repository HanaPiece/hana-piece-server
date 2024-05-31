package com.project.hana_piece.goal.dto;

public record UserGoalUpsertRequest(
        Long userGoalId,
        String goalTypeCd,
        Long goalSpecificId,
        String goalBeginDate,
        Integer duration,
        Long amount
) {}
