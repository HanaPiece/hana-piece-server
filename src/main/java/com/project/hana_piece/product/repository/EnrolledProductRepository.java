package com.project.hana_piece.product.repository;

import com.project.hana_piece.goal.domain.UserGoal;
import com.project.hana_piece.product.domain.EnrolledProduct;
import com.project.hana_piece.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolledProductRepository extends JpaRepository<EnrolledProduct, Long> {
    List<EnrolledProduct> findByUserGoalUserUserId(Long userId);

    boolean existsByProductAndUserGoal(Product product, UserGoal userGoal);
}
