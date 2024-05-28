package com.project.hana_piece.account.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class AccountNotFoundException extends EntityNotFoundException {

    public AccountNotFoundException() {
        super("Could not found Account");
    }

    public AccountNotFoundException(Long id) {
        super("Could not found Account"+id);
    }
}
