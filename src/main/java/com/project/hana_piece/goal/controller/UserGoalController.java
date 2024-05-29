package com.project.hana_piece.goal.controller;

import com.project.hana_piece.ai.service.AiService;
import com.project.hana_piece.goal.dto.ApartmentGetResponse;
import com.project.hana_piece.goal.dto.ApartmentPricePredictRequest;
import com.project.hana_piece.goal.dto.ApartmentPricePredictResponse;
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
    public ResponseEntity<ApartmentPricePredictResponse> predictApartmentPrice(@RequestBody ApartmentPricePredictRequest request) {
        ApartmentPricePredictResponse response = apartmentService.predictApartmentPrice(request);
        return ResponseEntity.ok(response);
    }
}
