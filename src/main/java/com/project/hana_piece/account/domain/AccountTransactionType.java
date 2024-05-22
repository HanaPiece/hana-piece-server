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
 * 거래 타입 ex)요식, 쇼핑
 */
@Entity(name = "account_transaction_type")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransactionType extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountTransactionTypeId;

    @Column(name = "account_transaction_type_nm", length = 50)
    private String accountTransactionTypeNm;
}