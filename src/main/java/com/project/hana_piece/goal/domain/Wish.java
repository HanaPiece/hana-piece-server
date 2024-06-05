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

@Entity(name = "wishes")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishId;

    @Column(name = "wish_nm", length = 20, nullable = false)
    private String wishNm;

    @Column(name = "wish_price", nullable = false)
    private Long wishPrice;

    @Builder
    public Wish(String wishNm, Long wishPrice) {
        this.wishNm = wishNm;
        this.wishPrice = wishPrice;
    }
}