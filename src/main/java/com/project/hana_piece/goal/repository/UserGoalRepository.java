package com.project.hana_piece.goal.repository;

import com.project.hana_piece.goal.domain.UserGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserGoalRepository extends JpaRepository<UserGoal, Long> {
    List<UserGoal> findByUserUserId(Long userId);
    Optional<UserGoal> findByUserGoalIdAndUserUserId(Long userGoalId, Long userId);
}
