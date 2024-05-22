package com.project.hana_piece.goal.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "goal_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GoalType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goalTypeId;

    @Column(name = "goal_type_nm", length = 50)
    private String goalTypeNm;
}