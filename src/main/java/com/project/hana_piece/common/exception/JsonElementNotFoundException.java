package com.project.hana_piece.common.exception;

public class JsonElementNotFoundException extends RuntimeException{

    public JsonElementNotFoundException() {
        super("Could not found json element");
    }

    public JsonElementNotFoundException(String message) {
        super(message);
    }
}

