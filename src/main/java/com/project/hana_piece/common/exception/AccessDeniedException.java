package com.project.hana_piece.common.exception;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super("Access Denied");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
