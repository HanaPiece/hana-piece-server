package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.domain.AccountTransaction;
import com.project.hana_piece.common.util.LocalDateTimeUtil;

public record AccountTransactionGetResponse(Long amount, String transactionDate) {

    public static AccountTransactionGetResponse fromEntity(AccountTransaction accountTransaction) {
        return new AccountTransactionGetResponse(accountTransaction.getAmount(), LocalDateTimeUtil.localDateTimeToYMDFormat(accountTransaction.getCreatedAt()));
    }
}
