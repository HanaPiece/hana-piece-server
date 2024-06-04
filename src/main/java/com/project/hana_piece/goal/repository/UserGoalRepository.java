package com.project.hana_piece.goal.repository;

import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.goal.projection.UserGoalSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    List<UserGoal> findByUserUserId(Long userId);
    Optional<UserGoal> findByUserGoalIdAndUserUserId(Long userGoalId, Long userId);

    @Query(value = "WITH EnrolledProductData AS ("
            + "    SELECT "
            + "        ep.enrolled_product_id, "
            + "        ep.user_goal_id, "
            + "        p.product_nm "
            + "    FROM enrolled_products ep "
            + "    JOIN products p ON ep.product_id = p.product_id "
            + "), "
            + "TransactionData AS ("
            + "    SELECT "
            + "        ac.account_id, "
            + "        SUM(CASE WHEN act.account_transaction_type_cd != 'INTEREST' THEN act.amount ELSE 0 END) as principal, "
            + "        SUM(CASE WHEN act.account_transaction_type_cd = 'INTEREST' THEN act.amount ELSE 0 END) as interestAmount "
            + "    FROM account_transactions act "
            + "    JOIN accounts ac ON act.account_id = ac.account_id "
            + "    JOIN EnrolledProductData ed ON ac.enrolled_product_id = ed.enrolled_product_id "
            + "    WHERE act.created_at BETWEEN DATE_FORMAT(ac.created_at, '%Y-%m-%d') AND DATE_FORMAT(ac.maturity_date, '%Y-%m-%d') "
            + "    GROUP BY ac.account_id "
            + ") "
            + "SELECT "
            + "        ug.user_goal_id as userGoalId, "
            + "        ug.goal_alias as goalAlias, "
            + "        ug.goal_begin_date as goalBeginDate, "
            + "        ug.duration, "
            + "        ug.amount, "
            + "        GROUP_CONCAT(p.product_nm) as productNames, "
            + "        COALESCE(SUM(at.amount), 0) as savingMoney "
            + "    FROM user_goals ug "
            + "    LEFT JOIN enrolled_products ep ON ug.user_goal_id = ep.user_goal_id "
            + "    LEFT JOIN products p ON ep.product_id = p.product_id "
            + "    LEFT JOIN accounts a ON ep.enrolled_product_id = a.enrolled_product_id "
            + "    LEFT JOIN account_transactions at ON a.account_id = at.account_id "
            + "    WHERE ug.user_id = :userId "
            + "    GROUP BY ug.user_goal_id, ug.goal_alias, ug.goal_begin_date, ug.duration, ug.amount", nativeQuery = true)
    List<UserGoalSummary> findUserGoalList(@Param("userId") Long userId);

}