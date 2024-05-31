package com.project.hana_piece.account.dto;

public record AccountAutoDebitAdjustUpsertRequest(Long savingAccountAutoDebitId, Long savingAutoDebitAmount, Long lifeAccountAutoDebitId, Long lifeAutoDebitAmount, Long spareAccountAutoDebitId, Long spareAutoDebitAmount) {

}
