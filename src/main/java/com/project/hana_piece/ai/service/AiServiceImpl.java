package com.project.hana_piece.ai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;


/*
 * Gemini AI 전용 Service 클래스
 * 1. 요청 프롬프트와 응답 데이터의 구조화
 * 2. REST API 기반의 AI API 호출
 * 3. AI API 응답 파싱 후, 사용자 필요 데이터 가공
 */
public class AiServiceImpl implements AiService{

    private static final String BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent";

    public ResponseEntity<List<String>> callGenerativeLanguageApi(){
        try {
            WebClient webClient = WebClient.create(BASE_URL);

            return null;
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
