package com.project.hana_piece.account.exception;

import com.project.hana_piece.common.exception.ValueInvalidException;

public class AccountInvalidException extends ValueInvalidException {

    public AccountInvalidException() {
        super("Invalid Account");
    }

    public AccountInvalidException(Long id) {
        super("Invalid Account"+id);
    }
}
