package com.project.hana_piece.ai.service;

import static com.project.hana_piece.ai.vo.GeminiResponseField.TEXT;

import com.google.gson.JsonObject;
import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.exception.GeminiNetworkIOException;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.common.exception.JsonElementNotFoundException;
import com.project.hana_piece.common.exception.ValueInvalidException;
import com.project.hana_piece.common.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Gemini AI 전용 Service 클래스
 * 1. 요청 프롬프트와 응답 데이터의 구조화
 * 2. REST API 기반의 AI API 호출
 * 3. AI API 응답 파싱 후, 사용자 필요 데이터 가공
 */
@Service
@RequiredArgsConstructor
public class AiServiceImpl implements AiService{

    private final JsonUtil jsonUtil;

    @Value("${ai.gemini.base-url}")
    private String BASE_URL;
    @Value("${ai.gemini.api-key}")
    private String API_KEY;

    private GeminiCallResponse parseGeminiPromptMessage(String payload) {
        JsonObject jsonObject = jsonUtil.toJson(payload);
        String text = jsonUtil.extractProperty(jsonObject, TEXT.toString(), String.class);

        return new GeminiCallResponse(text);
    }

    /**
     * Gemini API 호출 결과를 반환한다.
     * TODO 단순 Gemini API를 호출 후 별도의 연산이 필요하지 않아 non-blocking 기반의 webflux의 이점을 활용하지 못하고 동기적으로 구현함. 추후 비동기적 처리가 필요할 경우 리팩토링 할 예정
     * @return GeminiCallResponse
     */
    public GeminiCallResponse callGenerativeLanguageApi(GeminiPrompt geminiPrompt) {
        WebClient webClient = WebClient.create(BASE_URL + API_KEY);

        try {
            String geminiApiResponseString = webClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(geminiPrompt.getTotalPrompt())
                .retrieve()
                .bodyToMono(String.class)
                .block();

            return parseGeminiPromptMessage(geminiApiResponseString);
        } catch (JsonElementNotFoundException jse){
            throw new ValueInvalidException();
        } catch (Exception e) {
            throw new GeminiNetworkIOException();
        }
    }

}
