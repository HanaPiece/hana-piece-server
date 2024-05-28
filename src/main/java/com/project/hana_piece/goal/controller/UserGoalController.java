package com.project.hana_piece.goal.controller;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.goal.dto.ApartmentGetResponse;
import com.project.hana_piece.goal.dto.ApartmentPricePredictRequest;
import com.project.hana_piece.goal.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-goals")
public class UserGoalController {

    private final ApartmentService apartmentService;
    private final AiService aiService;

    @GetMapping("/apartments")
    public ResponseEntity<List<ApartmentGetResponse>> getAllApartments() {
        List<ApartmentGetResponse> response = apartmentService.getAllApartments();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/apartments/predict")
    public ResponseEntity<GeminiCallResponse> predictApartmentPrice(@RequestBody ApartmentPricePredictRequest request) {
        // date를 년과 월로 분리
        String[] dateParts = request.date().split("-");
        String year = dateParts[0];
        String month = dateParts[1];

        // 가격을 억 단위로 변환
        double priceInBillion = request.price() / 10000.0;

        // 프롬프트 생성
        String prompt = String.format("%s년 %s월 가장 최근 거래가가 %.1f억인 %s %s %d평의 가격을 선형 회귀 모델을 이용해 오차범위 50퍼센트 가격 이내에서 중간 시나리오로 최대한 정확히 예측해서 '%.1f억' 형식으로 가격만 알려줘",
                year, month, priceInBillion, request.region(), request.apartmentName(), request.area(), priceInBillion);

        GeminiPrompt geminiPrompt = new GeminiPrompt(prompt);
        GeminiCallResponse response = aiService.callGenerativeLanguageApi(geminiPrompt);
        return ResponseEntity.ok(response);
    }
}
