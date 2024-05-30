package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.projection.AccountAutoDebitSummary;

public record AccountAutoDebitAdjustGetResponse(Long accountId, String accountType, String accountNumber, Long accountAutoDebitId, Long autoDebitAmount) {

    public static AccountAutoDebitAdjustGetResponse fromProjection(
        AccountAutoDebitSummary projection) {
        return new AccountAutoDebitAdjustGetResponse(projection.getAccountId(),
            projection.getAccountTypeCd(), projection.getAccountNumber(),
            projection.getAccountAutoDebitId(), projection.getAutoDebitAmount());
    }
}
