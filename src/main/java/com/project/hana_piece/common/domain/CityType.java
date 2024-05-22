package com.project.hana_piece.common.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "city_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CityType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityTypeId;

    @Column(name = "city_type_cd")
    private Integer cityTypeCd;

    @Column(name = "city_type_nm", length = 50)
    private String cityTypeNm;
}