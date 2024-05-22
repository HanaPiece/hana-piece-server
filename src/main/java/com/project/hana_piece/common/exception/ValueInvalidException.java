package com.project.hana_piece.common.exception;

public class ValueInvalidException extends RuntimeException{

    public ValueInvalidException() {
        super("Invalid value");
    }

    public ValueInvalidException(String message) {
        super(message);
    }
}
