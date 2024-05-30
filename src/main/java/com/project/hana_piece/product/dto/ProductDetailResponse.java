package com.project.hana_piece.product.dto;

import com.project.hana_piece.product.domain.Product;

import java.math.BigDecimal;

public record ProductDetailResponse(
        Long productId,
        String productNm,
        String interestTypeCd,
        BigDecimal interestRate,
        String imageUrl,
        String info,
        Integer termYear,
        String cautions,
        String depositProtection,
        String contractTerms
) {
    public static ProductDetailResponse fromProduct(Product product) {
        return new ProductDetailResponse(
                product.getProductId(),
                product.getProductNm(),
                product.getInterestTypeCd(),
                product.getInterestRate(),
                product.getImageUrl(),
                product.getInfo(),
                product.getTermYear(),
                product.getCautions(),
                product.getDepositProtection(),
                product.getContractTerms()
        );
    }
}
