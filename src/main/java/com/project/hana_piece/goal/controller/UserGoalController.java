package com.project.hana_piece.goal.controller;

import com.project.hana_piece.goal.dto.ApartmentGetResponse;
import com.project.hana_piece.goal.dto.ApartmentPricePredictRequest;
import com.project.hana_piece.goal.dto.ApartmentPricePredictResponse;
import com.project.hana_piece.goal.dto.CarGetResponse;
import com.project.hana_piece.goal.service.ApartmentService;
import com.project.hana_piece.goal.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user-goals")
public class UserGoalController {

    private final ApartmentService apartmentService;
    private final CarService carService;

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

    @GetMapping("/cars")
    public ResponseEntity<List<CarGetResponse>> getAllCars() {
        List<CarGetResponse> response = carService.getAllCars();
        return ResponseEntity.ok(response);
    }
}
