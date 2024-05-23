package com.project.hana_piece.user.exception;

import com.project.hana_piece.common.exception.ValueInvalidException;

public class UserInvalidException extends ValueInvalidException {

    public UserInvalidException() {
        super("Invalid User");
    }

    public UserInvalidException(Long id) {
        super("Invalid User"+id);
    }
}
