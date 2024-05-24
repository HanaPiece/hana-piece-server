package com.project.hana_piece.account.domain;


/**
 * 계좌 타입
 * CHECKING 입출금
 * LIFE 생활
 * SAVING 적금
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