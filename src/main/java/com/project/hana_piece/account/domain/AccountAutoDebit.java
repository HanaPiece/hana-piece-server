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

@Entity(name = "account_auto_debit")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountAutoDebit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountAutoDebitId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "target_account_id")
    private Long targetAccountId;

    @Column(name = "auto_debit_amount")
    private Long autoDebitAmount;

    @Column(name = "auto_debit_day")
    private Integer autoDebitDay;

    public void setAccount(Account account) {
        this.account = account;
    }
    public void setAutoDebitAmount(Long amount) {
        this.autoDebitAmount = amount;
    }
    public void setAutoDebitDay(Integer day) { this.autoDebitDay = day;}

    @Builder
    public AccountAutoDebit(Account account, Long targetAccountId, Long autoDebitAmount,
        Integer autoDebitDay) {
        this.account = account;
        this.targetAccountId = targetAccountId;
        this.autoDebitAmount = autoDebitAmount;
        this.autoDebitDay = autoDebitDay;
    }
}