package com.project.hana_piece.goal.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import com.project.hana_piece.user.domain.User;
import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "goal_alias")
    private String goalAlias;

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

    public void setUser(User user) { this.user = user; }

    public void setGoalAlias(String goalAlias){ this.goalAlias = goalAlias; }

    public void setGoalTypeCd(GoalType goalTypeCd){ this.goalTypeCd = goalTypeCd.getProperty(); }

    public void setGoalSpecificId(Long goalSpecificId) { this.goalSpecificId = goalSpecificId; }

    public void setGoalBeginDate(String goalBeginDate) { this.goalBeginDate = goalBeginDate; }

    public void setDuration(Integer duration) { this.duration = duration; }

    public void setAmount(Long amount) { this.amount = amount; }

    @Builder
    public UserGoal(User user, String goalAlias, String goalTypeCd, Long goalSpecificId, String goalBeginDate, Integer duration, Long amount) {
        this.user = user;
        this.goalAlias = goalAlias;
        this.goalTypeCd = goalTypeCd;
        this.goalSpecificId = goalSpecificId;
        this.goalBeginDate = goalBeginDate;
        this.duration = duration;
        this.amount = amount;
    }
}