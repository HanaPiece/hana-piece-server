package com.project.hana_piece.account.projection;

import java.time.LocalDateTime;

public interface UserGoalAccountSummary {

    Long getAccountId();
    String getProductNm();
    String getAccountNumber();
    LocalDateTime getOpeningDate();
    Long getPrincipal();
    Long getTargetAmount();
    Long getInterestAmount();
}
