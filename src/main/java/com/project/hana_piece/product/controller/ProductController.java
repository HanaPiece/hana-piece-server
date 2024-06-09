package com.project.hana_piece.product.controller;

import com.project.hana_piece.product.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.project.hana_piece.product.service.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductGetResponse>> findProductList() {
        List<ProductGetResponse> response = productService.findProducts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/recommend/{userGoalId}")
    public ResponseEntity<RecommendationResponse> recommendProducts(@PathVariable Long userGoalId) {
        RecommendationResponse response = productService.recommendProducts(userGoalId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable Long productId) {
        ProductDetailResponse response = productService.getProductDetail(productId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/enroll")
    public ResponseEntity<Void> enrollProduct(@RequestBody EnrollProductRequest request) {
        productService.enrollProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
