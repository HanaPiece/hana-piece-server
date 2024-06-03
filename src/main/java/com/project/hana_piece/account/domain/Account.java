package com.project.hana_piece.account.domain;

import com.project.hana_piece.common.domain.BaseEntity;
import com.project.hana_piece.product.domain.EnrolledProduct;
import com.project.hana_piece.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "accounts")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrolled_product_id")
    private EnrolledProduct enrolledProduct;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "account_number", length = 200)
    private String accountNumber;

    @Column(name = "account_type_cd")
    private String accountTypeCd;

    @Column(name = "account_alias", length = 50)
    private String accountAlias;

    @Column(name = "balance")
    private Long balance;

    public void setAccountTypeCd(AccountType accountType){
        this.accountTypeCd = accountType.getProperty();
    }

    @Builder
    public Account(EnrolledProduct enrolledProduct, User user, String accountNumber,
        AccountType accountType, String accountAlias, Long balance) {
        this.enrolledProduct = enrolledProduct;
        this.user = user;
        this.accountNumber = accountNumber;
        this.accountTypeCd = accountType.toString();
        this.accountAlias = accountAlias;
        this.balance = balance;
    }
}
