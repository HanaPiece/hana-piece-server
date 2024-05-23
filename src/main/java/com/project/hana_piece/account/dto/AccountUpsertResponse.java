package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.domain.Account;

public record AccountUpsertResponse() {

    public static AccountUpsertResponse fromEntity(Account account){
        return new AccountUpsertResponse();
    }
}
