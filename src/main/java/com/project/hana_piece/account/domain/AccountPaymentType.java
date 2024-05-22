package com.project.hana_piece.account.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 결제 타입 ex)계좌이체, 카드결제
 */
@Entity(name = "account_payment_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountPaymentType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountPaymentTypeId;

    @Column(name = "account_payment_type_nm", length = 50)
    private String accountPaymentTypeNm;
}
