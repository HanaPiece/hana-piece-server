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

    @Query(value = "SELECT "
            + "        ug.user_goal_id as userGoalId, "
            + "        ug.goal_alias as goalAlias, "
            + "        ug.goal_type_cd as goalTypeCd, "
            + "        ug.goal_specific_id as goalSpecificId, "
            + "        ug.goal_begin_date as goalBeginDate, "
            + "        ug.duration, "
            + "        ug.amount, "
            + "        GROUP_CONCAT(DISTINCT p.product_nm) as productNames, "
            + "        GROUP_CONCAT(DISTINCT ep.enrolled_product_id) as productIds, "
            + "        COALESCE(SUM(at.amount), 0) as savingMoney, "
            + "        COALESCE(SUM(CASE WHEN at.account_transaction_type_cd = 'INTEREST' THEN at.amount ELSE 0 END), 0) as totalInterest "
            + "    FROM user_goals ug "
            + "    LEFT JOIN enrolled_products ep ON ug.user_goal_id = ep.user_goal_id "
            + "    LEFT JOIN products p ON ep.product_id = p.product_id "
            + "    LEFT JOIN accounts a ON ep.enrolled_product_id = a.enrolled_product_id "
            + "    LEFT JOIN account_transactions at ON a.account_id = at.account_id "
            + "    WHERE ug.user_id = :userId "
            + "    GROUP BY ug.user_goal_id, ug.goal_alias, ug.goal_type_cd, ug.goal_specific_id, ug.goal_begin_date, ug.duration, ug.amount", nativeQuery = true)
    List<UserGoalSummary> findUserGoalList(@Param("userId") Long userId);
}