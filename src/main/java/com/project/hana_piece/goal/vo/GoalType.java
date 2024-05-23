package com.project.hana_piece.goal.vo;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 목표 타입
 * HOUSE 집
 * CAR 생활
 * WISH 소원
 */
public enum GoalType  {

    HOUSE("HOUSE"),
    CAR("CAR"),
    WISH("WISH");
    private final String property;

    GoalType(String property) {
        this.property = property;
    }
}