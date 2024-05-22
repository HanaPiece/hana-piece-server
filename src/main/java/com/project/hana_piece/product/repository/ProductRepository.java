package com.project.hana_piece.product.repository;

import com.project.hana_piece.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
