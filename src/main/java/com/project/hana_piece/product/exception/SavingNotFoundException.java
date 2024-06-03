package com.project.hana_piece.product.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class SavingNotFoundException extends EntityNotFoundException {
    public SavingNotFoundException() {
        super("Could not find Saving Account");
    }
}
