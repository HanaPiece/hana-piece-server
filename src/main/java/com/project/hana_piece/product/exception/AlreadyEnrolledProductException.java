package com.project.hana_piece.product.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class AlreadyEnrolledProductException extends EntityNotFoundException {
    public AlreadyEnrolledProductException() {
        super("This product is already enrolled.");
    }

    public AlreadyEnrolledProductException(Long productId) {
        super("This product is already enrolled with product ID: " + productId);
    }
}
