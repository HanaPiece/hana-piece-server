package com.project.hana_piece.goal.controller;

import com.project.hana_piece.goal.dto.ApartmentGetResponse;
import com.project.hana_piece.goal.service.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-goals")
public class UserGoalController {

    private final ApartmentService apartmentService;

    @GetMapping("/apartments")
    public ResponseEntity<List<ApartmentGetResponse>> getAllApartments() {
        List<ApartmentGetResponse> response = apartmentService.getAllApartments();
        return ResponseEntity.ok(response);
    }
}
