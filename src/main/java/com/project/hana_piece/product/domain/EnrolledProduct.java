package com.project.hana_piece.product.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import com.project.hana_piece.goal.domain.UserGoal;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;

@Entity(name = "enrolled_products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Long autoDebitAmount;

    @Column(name="auto_debit_day")
    private Integer  autoDebitDay;

    @Column(name="maturity_date")
    private String  maturityDate;   //yyyyMM

    @Column(name="auto_renewal")
    private boolean autoRenewal;   //yyyyMM

    @Builder
    public EnrolledProduct(Product product, UserGoal userGoal, Integer contractPeriod,
        BigInteger initialAmount, Long autoDebitAmount, Integer autoDebitDay, String maturityDate,
        boolean autoRenewal) {
        this.product = product;
        this.userGoal = userGoal;
        this.contractPeriod = contractPeriod;
        this.initialAmount = initialAmount;
        this.autoDebitAmount = autoDebitAmount;
        this.autoDebitDay = autoDebitDay;
        this.maturityDate = maturityDate;
        this.autoRenewal = autoRenewal;
    }
}