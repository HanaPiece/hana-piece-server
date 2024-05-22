package com.project.hana_piece.product.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name="product_nm")
    private String productNm;

    @Column(name="interest_type")
    private String interestType;

    @Column(name="interest_rate")
    private BigDecimal interestRate;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="info")
    private String info;

    @Column(name="term_year")
    private Integer termYear;

    @Column(name="cautions")
    private String cautions;

    @Column(name="deposit_protection")
    private String depositProtection;

    @Column(name="contract_terms")
    private String contractTerms;
}
