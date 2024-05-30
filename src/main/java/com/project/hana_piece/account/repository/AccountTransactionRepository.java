package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.AccountTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction, Long> {

    List<AccountTransaction> findByAccountAccountId(Long accountId);

    @Query(value = "SELECT *" +
        "FROM account_transactions act " +
        "WHERE DATE_FORMAT(act.created_at, '%m') = :transactionMonth " +
        "AND act.account_id = :accountId " +
        "AND act.amount < 0", nativeQuery = true)
    List<AccountTransaction> findDailyTransactionList(@Param("accountId") Long accountId, @Param("transactionMonth") Integer transactionMonth);
}
