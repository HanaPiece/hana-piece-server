package com.project.hana_piece.goal.repository;

import com.project.hana_piece.goal.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishRepository extends JpaRepository<Wish, Long> {
}
