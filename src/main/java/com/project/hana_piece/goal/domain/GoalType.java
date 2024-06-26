package com.project.hana_piece.goal.domain;

import lombok.Getter;

/**
 * 목표 타입
 * HOUSE 집
 * CAR 생활
 * WISH 소원
 */
@Getter
public enum GoalType  {

    HOUSE("HOUSE"),
    CAR("CAR"),
    WISH("WISH");
    private final String property;

    GoalType(String property) {
        this.property = property;
    }
}