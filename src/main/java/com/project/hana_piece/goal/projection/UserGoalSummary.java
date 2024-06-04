package com.project.hana_piece.goal.projection;

public interface UserGoalSummary {
    Long getUserGoalId();
    String getGoalAlias();
    String getGoalBeginDate();
    Integer getDuration();
    Long getAmount();
    String getProductNames();  // List<String> 형태로 변환될 문자열
    Long getSavingMoney();
}