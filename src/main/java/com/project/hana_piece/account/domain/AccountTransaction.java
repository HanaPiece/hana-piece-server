package com.project.hana_piece.account.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "account_transactions")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountTransaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountTransactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "account_payment_type_cd")
    private String accountPaymentTypeCd;

    @Column(name = "account_transaction_type_cd")
    private String accountTransactionTypeCd;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "old_balance")
    private Long oldBalance;

    @Column(name = "new_balance")
    private Long newBalance;

    @Column(name = "target_nm")
    private String targetNm;

    @Column(name = "description", length = 300)
    private String description;

    @Builder
    public AccountTransaction(Account account, String accountPaymentTypeCd,
        String accountTransactionTypeCd, Long amount, Long oldBalance, Long newBalance,
        String targetNm,
        String description) {
        this.account = account;
        this.accountPaymentTypeCd = accountPaymentTypeCd;
        this.accountTransactionTypeCd = accountTransactionTypeCd;
        this.amount = amount;
        this.oldBalance = oldBalance;
        this.newBalance = newBalance;
        this.targetNm = targetNm;
        this.description = description;
    }
}
