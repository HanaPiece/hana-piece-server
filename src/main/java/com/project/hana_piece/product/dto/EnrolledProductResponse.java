package com.project.hana_piece.product.dto;

import com.project.hana_piece.product.domain.EnrolledProduct;

public record EnrolledProductResponse(Long enrolledProductId, String productNm) {
    public EnrolledProductResponse(EnrolledProduct enrolledProduct) {
        this(
                enrolledProduct.getEnrolledProductId(),
                enrolledProduct.getProduct().getProductNm()
        );
    }

}
