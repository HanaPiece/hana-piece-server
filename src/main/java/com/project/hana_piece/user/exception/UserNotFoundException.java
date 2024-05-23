package com.project.hana_piece.user.exception;

import com.project.hana_piece.common.exception.EntityNotFoundException;

public class UserNotFoundException extends EntityNotFoundException {

    public UserNotFoundException() {
        super("Could not found user");
    }

    public UserNotFoundException(Long id) {
        super("Could not found user"+id);
    }
}
