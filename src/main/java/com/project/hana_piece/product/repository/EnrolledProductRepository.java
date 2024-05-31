package com.project.hana_piece.product.repository;

import com.project.hana_piece.product.domain.EnrolledProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrolledProductRepository extends JpaRepository<EnrolledProduct, Long> {
    List<EnrolledProduct> findByUserGoalUserGoalId(Long userGoalId);
}
