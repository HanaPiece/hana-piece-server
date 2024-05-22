package com.project.hana_piece.product.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "interest_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterestType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interestTypeId;

    @Column(name = "interest_type_nm", length = 50)
    private String interestTypeNm;
}