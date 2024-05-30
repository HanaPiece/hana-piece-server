package com.project.hana_piece.account.repository;

import com.project.hana_piece.account.domain.Account;
import com.project.hana_piece.account.projection.UserGoalAccountSummary;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts " +
        "WHERE account_type_cd IN('CHECKING','SALARY','LIFE','SAVING','SPARE') " +
        "AND user_id = :userId " +
        "ORDER BY account_type_cd ASC, account_id ASC", nativeQuery = true)
    List<Account> findCheckingAccount(Long userId);

    @Query(value = "SELECT * FROM accounts " +
        "WHERE account_type_cd IN('INSTALLMENT_SAVING') " +
        "AND user_id = :userId " +
        "ORDER BY account_type_cd ASC, account_id ASC", nativeQuery = true)
    List<Account> findSavingAccount(Long userId);


    @Query(value = "WITH EnrolledProductData AS ("
        + "    SELECT "
        + "        ep.enrolled_product_id, "
        + "        ep.user_goal_id, "
        + "        ep.product_id, "
        + "        ep.created_at, "
        + "        ep.maturity_date, "
        + "        p.product_nm, "
        + "        ac.account_id, "
        + "        ac.account_number "
        + "    FROM enrolled_products ep "
        + "    JOIN products p ON ep.product_id = p.product_id "
        + "    JOIN accounts ac ON ep.enrolled_product_id = ac.enrolled_product_id "
        + "    WHERE ep.user_goal_id = :userGoalId "
        + "), "
        + "TransactionData AS ("
        + "    SELECT "
        + "        ac.account_id, "
        + "        SUM(CASE WHEN act.account_transaction_type_cd != 'INTEREST' THEN act.amount ELSE 0 END) as principal, "
        + "        SUM(CASE WHEN act.account_transaction_type_cd = 'INTEREST' THEN act.amount ELSE 0 END) as interestAmount "
        + "    FROM account_transactions act "
        + "    JOIN accounts ac ON act.account_id = ac.account_id "
        + "    JOIN EnrolledProductData ed ON ac.account_id = ed.account_id "
        + "    WHERE act.created_at BETWEEN DATE_FORMAT(ed.created_at, '%Y-%m-%d') AND DATE_FORMAT(ed.maturity_date, '%Y-%m-%d') "
        + "    GROUP BY ac.account_id "
        + ") "
        + "SELECT "
        + "        ed.account_id as accountId, "
        + "        ed.product_nm as productNm, "
        + "        ed.account_number as accountNumber, "
        + "        ed.created_at as openingDate, "
        + "        td.principal as principal, "
        + "        ug.amount as targetAmount, "
        + "        td.interestAmount as interestAmount "
        + "    FROM EnrolledProductData ed "
        + "    JOIN TransactionData td ON ed.account_id = td.account_id "
        + "    JOIN user_goals ug ON ed.user_goal_id = ug.user_goal_id", nativeQuery = true)
    List<UserGoalAccountSummary> findUserGoalAccountList(@Param("userGoalId") Long userGoalId);

    @Query(value = "SELECT * FROM accounts " +
        "WHERE account_type_cd IN('SALARY') " +
        "AND user_id = :userId " +
        "LIMIT 1", nativeQuery = true)
    Optional<Account> findSalaryAccount(Long userId);
}