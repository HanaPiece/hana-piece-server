package com.project.hana_piece.goal.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "user_goals")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserGoal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userGoalId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "goal_type_cd")
    private String goalTypeCd;

    @Column(name = "goal_specific_id")
    private Long goalSpecificId;

    @Column(name = "goal_begin_date")
    private String goalBeginDate;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "amount")
    private Long amount;

    @Builder
    public UserGoal(Long userId, String goalTypeCd, Long goalSpecificId, String goalBeginDate, Integer duration, Long amount) {
        this.userId = userId;
        this.goalTypeCd = goalTypeCd;
        this.goalSpecificId = goalSpecificId;
        this.goalBeginDate = goalBeginDate;
        this.duration = duration;
        this.amount = amount;
    }
}