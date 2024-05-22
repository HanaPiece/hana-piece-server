package com.project.hana_piece.ai.vo;

import lombok.Getter;

@Getter
public class GeminiPrompt {

    private final String totalPrompt;

    public GeminiPrompt(String prompt) {
        this.totalPrompt = createPromptJson(prompt);
    }

    private String createPromptJson(String prompt) {
        return "{\"contents\":[{\"parts\":[{\"text\":\""+prompt+"\"}]}]}";
    }
}
