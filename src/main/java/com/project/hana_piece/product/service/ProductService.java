package com.project.hana_piece.product.service;

import com.project.hana_piece.product.domain.Product;
import com.project.hana_piece.product.dto.ProductGetResponse;
import com.project.hana_piece.product.repository.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductGetResponse> findProducts(){
        List<Product> products = productRepository.findAll();
        return null;
    }
}
