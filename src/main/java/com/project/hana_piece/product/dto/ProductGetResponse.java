package com.project.hana_piece.product.dto;

import com.project.hana_piece.product.domain.Product;

import java.math.BigDecimal;

public record ProductGetResponse(Long productId, String productNm, Integer termYear, BigDecimal interestRate) {

    public static ProductGetResponse fromProduct(Product product){
        return new ProductGetResponse(
                product.getProductId(),
                product.getProductNm(),
                product.getTermYear(),
                product.getInterestRate()
        );
    }

}
