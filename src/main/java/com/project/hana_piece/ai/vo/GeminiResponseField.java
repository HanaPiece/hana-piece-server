package com.project.hana_piece.ai.vo;

import lombok.Getter;

@Getter
public enum GeminiResponseField {

    TEXT("text"),
    NONE("none");

    private final String property;

    GeminiResponseField(String property) {
        this.property = property;
    }

}
