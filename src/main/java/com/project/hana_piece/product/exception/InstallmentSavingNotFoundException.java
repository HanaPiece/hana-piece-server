package com.project.hana_piece.product.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class InstallmentSavingNotFoundException extends EntityNotFoundException {
    public InstallmentSavingNotFoundException() {
        super("Could not find SAVING account.");
    }
}