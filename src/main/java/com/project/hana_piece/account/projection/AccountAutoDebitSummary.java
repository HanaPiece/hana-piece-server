package com.project.hana_piece.account.projection;

public interface AccountAutoDebitSummary {

    Long getAccountId();
    String getAccountTypeCd();
    String getAccountNumber();
    Long getAccountAutoDebitId();
    Long getAutoDebitAmount();
}
