package com.project.hana_piece.product.dto;

import java.util.List;

public record RecommendationResponse(
        List<ProductGetResponse> recommendedProducts,
        List<EnrolledProductResponse> enrolledProducts
) {}
