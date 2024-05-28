package com.project.hana_piece.account.dto;

public record AccountTypeRegRequest(Long salaryAccountId, Long savingAccountId, Long lifeAccountId, Long spareAccountId) {

    public AccountTypeRegRequest {
        if (salaryAccountId == null) {
            salaryAccountId = -1L;
        }
        if (savingAccountId == null) {
            savingAccountId = -1L;
        }
        if (lifeAccountId == null) {
            lifeAccountId = -1L;
        }
        if (spareAccountId == null) {
            spareAccountId = -1L;
        }
    }

}
