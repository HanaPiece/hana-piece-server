package com.project.hana_piece.product.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.exception.UserGoalNotFoundException;
import com.project.hana_piece.product.dto.EnrollProductRequest;
import com.project.hana_piece.product.exception.ProductNotFoundException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigInteger;

import lombok.*;

@Entity(name = "enrolled_products")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EnrolledProduct extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enrolledProductId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_goal_id")
    private UserGoal userGoal;

    @Column(name="contract_period")
    private Integer contractPeriod;

    @Column(name="initial_amount")
    private BigInteger initialAmount;

    @Column(name="auto_debit_amount")
    private BigInteger autoDebitAmount;

    @Column(name="auto_debit_day")
    private Integer  autoDebitDay;

    @Column(name="maturity_date")
    private String  maturityDate;   //yyyyMM

    @Column(name="auto_renewal")
    private boolean autoRenewal;   //yyyyMM

    public Product getProduct() {
        return product;
    }
}