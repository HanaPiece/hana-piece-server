package com.project.hana_piece.ai.controller;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    // TODO 참고용 추후 제거 예정
    @GetMapping("/ai")
    private ResponseEntity<GeminiCallResponse> aiTest() {
        GeminiPrompt geminiPrompt = new GeminiPrompt("도승희는 누구야?");
        GeminiCallResponse response = aiService.callGenerativeLanguageApi(geminiPrompt);
        return ResponseEntity.ok(response);
    }

}
