package com.project.hana_piece.product.dto;

import com.project.hana_piece.product.domain.EnrolledProduct;

public record EnrolledProductResponse(Long enrolledProductId, String productNm) {
    public static EnrolledProductResponse fromEntity(EnrolledProduct enrolledProduct) {
        return new EnrolledProductResponse(
                enrolledProduct.getEnrolledProductId(),
                enrolledProduct.getProduct().getProductNm()
        );
    }

}
