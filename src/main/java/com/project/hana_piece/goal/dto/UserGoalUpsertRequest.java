package com.project.hana_piece.goal.dto;

public record UserGoalUpsertRequest(
        Long userGoalId,  // 수정 시 필요, 생성 시에는 null
        String goalTypeCd,
        Long goalSpecificId,
        String goalBeginDate,
        Integer duration,
        Long amount
) {}
