package com.project.hana_piece.ai.vo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeminiPrompt {

    private String requests;
    private String constraints;
    private String responseFormat;
    private String exampleData;

    public synchronized String getTotalPrompt() {
        StringBuilder totalPromptBuilder = new StringBuilder();
        totalPromptBuilder.append(requests)
            .append(constraints)
            .append(responseFormat)
            .append(exampleData);
        return totalPromptBuilder.toString();
    }

    @Builder
    public GeminiPrompt(String requests, String constraints, String responseFormat,
        String exampleData) {
        this.requests = "[Requests] " + requests + "\n";
        this.constraints = "[Constraints] " + constraints + "\n";
        this.responseFormat = "[ResponseFormat] " + responseFormat + "\n";
        this.exampleData = "[ExampleData] "+ exampleData + "\n";
    }
}
