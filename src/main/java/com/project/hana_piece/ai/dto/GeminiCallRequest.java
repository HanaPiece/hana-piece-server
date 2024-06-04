package com.project.hana_piece.ai.dto;

import com.project.hana_piece.ai.vo.GeminiPrompt;
import java.util.List;

public record GeminiCallRequest(List<Content> contents, List<SafetySetting> safetySettings, GenerationConfig generationConfig) {

    private static final String safetySettingCategory = "HARM_CATEGORY_DANGEROUS_CONTENT";
    private static final String safetySettingThreshold = "BLOCK_ONLY_HIGH";
    private static final Double generationConfigTemperature = 0.1;
    private static final Double generationConfigTopP = 0.1;
    private static final Integer generationConfigTopK = 10;

    public static record Part(String text) {}

    public static record Content(List<Part> parts) {}

    public static record SafetySetting(String category, String threshold) {}

    public static record GenerationConfig(Double temperature, Double topP, Integer topK) {}

    public static GeminiCallRequest fromPrompt(GeminiPrompt geminiPrompt){
        Part part = new Part(geminiPrompt.getTotalPrompt());
        Content content = new Content(List.of(part));
        List<Content> contents = List.of(content);

        SafetySetting safetySetting = new SafetySetting(safetySettingCategory, safetySettingThreshold);
        List<SafetySetting> safetySettings = List.of(safetySetting);

        GenerationConfig generationConfig = new GenerationConfig(generationConfigTemperature, generationConfigTopP, generationConfigTopK);

        return new GeminiCallRequest(contents, safetySettings, generationConfig);
    }
}
