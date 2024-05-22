package com.project.hana_piece.ai.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

public interface AiService {

    ResponseEntity<List<String>> callGenerativeLanguageApi();
}
