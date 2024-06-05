package com.project.hana_piece.account.repository;

import static com.project.hana_piece.account.domain.QAccountAutoDebit.accountAutoDebit;
import static com.project.hana_piece.account.domain.QAccountTransaction.accountTransaction;

import com.project.hana_piece.account.domain.AccountTransaction;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AccountTransactionRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    public List<AccountTransaction> findDailyTransactionList(Long accountId, Integer transactionYearMonth){
        return queryFactory.selectFrom(accountTransaction)
            .where(accountTransaction.createdAt.yearMonth().eq(transactionYearMonth)
                .and(accountTransaction.account.accountId.eq(accountId))
                .and(accountTransaction.amount.lt(0)))
            .orderBy(accountTransaction.createdAt.desc())
            .fetch();
    }

    public Long findSumAutoDebitAmount(Long accountId, Integer transactionYearMonth){
        return queryFactory.select(accountAutoDebit.autoDebitAmount.sum())
            .from(accountAutoDebit)
            .where(accountAutoDebit.targetAccountId.eq(accountId))
            .fetchOne();
    }
}
