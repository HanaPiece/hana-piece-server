package com.project.hana_piece.goal.repository;

import com.project.hana_piece.goal.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
