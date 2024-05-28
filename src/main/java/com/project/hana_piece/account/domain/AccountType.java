package com.project.hana_piece.account.domain;


import lombok.Getter;

/**
 * 계좌 타입
 * CHECKING 입출금
 * SALARY 월급
 * LIFE 생활
 * SAVING 저축
 * SPARE 예비
 * INSTALLMENT_SAVING 적금
 * PARKING 저금통
 */
@Getter
public enum AccountType  {

    CHECKING("CHECKING"),
    SALARY("SALARY"),
    LIFE("LIFE"),
    SAVING("SAVING"),
    SPARE("SPARE"),
    INSTALLMENT_SAVING("INSTALLMENT_SAVING"),
    PARKING("PARKING");
    private final String property;

    AccountType(String property) {
        this.property = property;
    }

    public static boolean isParkingAccountType(String accountType) {
        if(accountType == PARKING.getProperty()) return true;
        return false;
    }
}