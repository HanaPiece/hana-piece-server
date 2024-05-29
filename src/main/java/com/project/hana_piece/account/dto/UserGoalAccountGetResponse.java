package com.project.hana_piece.account.dto;

import com.project.hana_piece.account.projection.UserGoalAccountSummary;
import com.project.hana_piece.common.util.LocalDateTimeUtil;

public record UserGoalAccountGetResponse(Long accountId, String productNm, String accountNumber, String openingDate, Long principal, Long targetAmount, Long interestAmount) {

    public static UserGoalAccountGetResponse fromProjection(UserGoalAccountSummary projection) {
        return new UserGoalAccountGetResponse(projection.getAccountId(), projection.getProductNm(),
            projection.getAccountNumber(), LocalDateTimeUtil.localDateTimeToYMDFormat(projection.getOpeningDate()), projection.getPrincipal(), projection.getTargetAmount(),
            projection.getInterestAmount());
    }
}
