package com.project.hana_piece.common.exception;

public class NetworkIOException extends RuntimeException {

    public NetworkIOException() {
        super("Network IO Problem occurred");
    }

    public NetworkIOException(String message) {
        super(message);
    }
}
