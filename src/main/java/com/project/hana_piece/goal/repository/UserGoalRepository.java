package com.project.hana_piece.goal.repository;

import com.project.hana_piece.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGoalRepository extends JpaRepository<User, Long> {

}
