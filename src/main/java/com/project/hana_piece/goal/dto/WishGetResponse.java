package com.project.hana_piece.goal.dto;

import com.project.hana_piece.goal.domain.Wish;

public record WishGetResponse(String wishNm, Long wishPrice) {

    public static WishGetResponse fromEntity(Wish wish) {
        return new WishGetResponse(
                wish.getWishNm(),
                wish.getWishPrice()
        );
    }
}
