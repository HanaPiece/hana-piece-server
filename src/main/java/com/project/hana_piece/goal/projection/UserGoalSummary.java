package com.project.hana_piece.goal.projection;

public interface UserGoalSummary {
    Long getUserGoalId();
    String getGoalAlias();
    String getGoalBeginDate();
    String getGoalTypeCd();
    Long getGoalSpecificId();
    Integer getDuration();
    Long getAmount();
    String getProductIds();
    String getProductNames();
    Long getSavingMoney();
}
