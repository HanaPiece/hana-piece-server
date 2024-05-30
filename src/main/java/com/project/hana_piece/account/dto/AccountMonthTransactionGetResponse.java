package com.project.hana_piece.account.dto;

import java.util.List;
import java.util.Map;

public record AccountMonthTransactionGetResponse(Long monthlySum, Map<String, Long> amountByType, Map<Integer, Long> amountByDay, List<AccountDailyTransactionGetResponse> dailyTransactionList) {
}
