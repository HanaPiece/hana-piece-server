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
 * 계좌 타입
 * CHECKING 입출금
 * LIFE 생활
 * SAVING 쇼핑
 * SPARE 예비
 * PARKING 저금통
 */
public enum AccountType  {

    CHECKING("CHECKING"),
    LIFE("LIFE"),
    SAVING("SAVING"),
    SPARE("SPARE"),
    PARKING("PARKING");
    private final String property;

    AccountType(String property) {
        this.property = property;
    }
}