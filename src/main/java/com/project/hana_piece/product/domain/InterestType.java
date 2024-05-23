package com.project.hana_piece.product.domain;

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
 * 이자율 타입
 * FIX 고정
 * VARIABLE 변동
 */
public enum InterestType  {

    FIX("FIX"),
    VARIABLE("VARIABLE");
    private final String property;

    InterestType(String property) {
        this.property = property;
    }
}