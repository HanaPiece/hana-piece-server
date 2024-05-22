package com.project.hana_piece.ai.service;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.vo.GeminiPrompt;

public interface AiService {

    GeminiCallResponse callGenerativeLanguageApi(GeminiPrompt geminiPrompt);
}
