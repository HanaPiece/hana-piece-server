package com.project.hana_piece.goal.repository;

import com.project.hana_piece.goal.domain.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, Long> {
}
