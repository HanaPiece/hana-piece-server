package com.project.hana_piece.account.projection;

public interface AccountDailyTransactionSummary {

    Integer getTransactionDay();
    Long getDayAmount();
    String getAccountTransactionType();
}
