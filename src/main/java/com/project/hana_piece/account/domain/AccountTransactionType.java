package com.project.hana_piece.account.domain;

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
 * 거래 타입
 * FOOD 요식
 * TRANSPORT 교통
 * SHOPPING 쇼핑
 * TRANSFER 계좌 이체
 */
@Getter
public enum AccountTransactionType {

    FOOD("FOOD"),
    TRANSFER("TRANSFER"),
    SHOPPING("SHOPPING"),
    TRANSPORT("TRANSPORT");
    private final String property;

    AccountTransactionType(String property) {
        this.property = property;
    }
}