package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.domain.Account;

public record AccountGetResponse(Long accountId, String accountNumber, String accountTypeCd) {

    public static AccountGetResponse fromEntity(Account account){
        return new AccountGetResponse(account.getAccountId(), account.getAccountNumber(),
            account.getAccountTypeCd());
    }
}
