package com.project.hana_piece.product.controller;

import com.project.hana_piece.product.dto.ProductDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import com.project.hana_piece.product.dto.ProductGetResponse;
import com.project.hana_piece.product.service.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductGetResponse>> findProductList() {
        List<ProductGetResponse> response = productService.findProducts();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/products/recommend")
    public ResponseEntity<List<ProductGetResponse>> recommendProducts(@RequestParam String category) {
        List<ProductGetResponse> response = productService.recommendProducts(category);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDetailResponse> getProductDetail(@PathVariable Long productId) {
        ProductDetailResponse response = productService.getProductDetail(productId);
        return ResponseEntity.ok(response);
    }
}
