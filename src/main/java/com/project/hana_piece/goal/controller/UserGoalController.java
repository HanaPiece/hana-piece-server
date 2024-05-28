package com.project.hana_piece.goal.controller;

import com.project.hana_piece.ai.dto.GeminiCallResponse;
import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.ai.vo.GeminiPrompt;
import com.project.hana_piece.goal.domain.Apartment;
import com.project.hana_piece.goal.service.ApartmentService;
import com.project.hana_piece.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/goals")
public class UserGoalController {

    private final UserService userService;
    private final ApartmentService apartmentService;
    private final AiService aiService;

    @GetMapping("/apartments")
    public ResponseEntity<List<Apartment>> getAllApartments() {
        List<Apartment> apartments = apartmentService.getAllApartments();
        return ResponseEntity.ok(apartments);
    }

}
