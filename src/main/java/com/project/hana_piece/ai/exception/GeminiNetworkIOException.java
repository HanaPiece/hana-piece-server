package com.project.hana_piece.ai.exception;

import com.project.hana_piece.common.exception.NetworkIOException;

public class GeminiNetworkIOException extends NetworkIOException {

    public GeminiNetworkIOException() {
        super("Gemini Network IO Problem occurred");
    }
}
