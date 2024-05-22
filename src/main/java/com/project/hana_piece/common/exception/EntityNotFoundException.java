package com.project.hana_piece.common.exception;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException() {
        super("Could not found entity");
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
