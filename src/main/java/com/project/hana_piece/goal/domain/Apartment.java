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

@Entity(name = "apartments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Apartment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long apartmentId;

    @Column(name = "apartment_nm", length = 30)
    private String apartmentNm;

    @Column(name = "apartment_price")
    private Long apartmentPrice;

    @Column(name = "region_cd")
    private Long regionCd;

    @Column(name = "region_nm", length = 30)
    private String regionNm;

    @Column(name = "exclusive_area")
    private Long exclusiveArea;
}