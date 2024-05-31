package com.project.hana_piece.account.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class AccountAutoDebitNotFoundException extends EntityNotFoundException {

    public AccountAutoDebitNotFoundException() {
        super("Could not found AccountAutoDebit");
    }

    public AccountAutoDebitNotFoundException(Long id) {
        super("Could not found AccountAutoDebit"+id);
    }
}

