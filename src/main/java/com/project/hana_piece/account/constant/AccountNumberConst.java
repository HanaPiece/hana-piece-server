package com.project.hana_piece.account.constant;

import lombok.Getter;

/**
 * 계좌 번호 관련 상수 데이터 관리
 */
@Getter
public enum AccountNumberConst {

    PREFIX(178),
    LENGTH_WITHOUT_PREFIX(11);
    private final Integer property;

    AccountNumberConst(Integer property) {
        this.property = property;
    }

}
