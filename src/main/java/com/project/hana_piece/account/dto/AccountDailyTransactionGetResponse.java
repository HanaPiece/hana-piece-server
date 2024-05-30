package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.domain.AccountTransaction;
import com.project.hana_piece.account.projection.AccountDailyTransactionSummary;
import com.project.hana_piece.common.util.LocalDateTimeUtil;

public record AccountDailyTransactionGetResponse(Integer transactionDay, Long amount, String accountTransactionType) {

    public static AccountDailyTransactionGetResponse fromEntity(AccountTransaction entity) {
        return new AccountDailyTransactionGetResponse(LocalDateTimeUtil.localDateTimeToDayFormat(entity.getCreatedAt()), entity.getAmount(), entity.getAccountTransactionTypeCd());
    }
}
