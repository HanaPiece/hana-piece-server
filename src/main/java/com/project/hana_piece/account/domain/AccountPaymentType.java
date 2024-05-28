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
 * 결제 타입
 * TRANSFER 계좌이체
 * CARD 카드결제
 */
@Getter
public enum AccountPaymentType {
    TRANSFER("TRANSFER"),
    CARD("CARD");
    private final String property;

    AccountPaymentType(String property) {
        this.property = property;
    }
}
