    package com.project.hana_piece.goal.controller;

    import com.project.hana_piece.goal.dto.*;
    import com.project.hana_piece.goal.service.ApartmentService;
    import com.project.hana_piece.goal.service.CarService;
    import com.project.hana_piece.goal.service.UserGoalService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/v1/user-goals")
    public class UserGoalController {

        private final UserGoalService userGoalService;
        private final ApartmentService apartmentService;
        private final CarService carService;

        @GetMapping
        public ResponseEntity<List<UserGoalGetResponse>> findUserGoals(@AuthenticationPrincipal Long userId) {
            List<UserGoalGetResponse> response = userGoalService.findUserGoalsByUserId(userId);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/types")
        public ResponseEntity<List<UserGoalTypeGetResponse>> findUserGoalTypesByUserId(@AuthenticationPrincipal Long userId) {
            List<UserGoalTypeGetResponse> response = userGoalService.findUserGoalTypesByUserId(userId);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/{goalUserId}")
        public ResponseEntity<UserGoalDetailGetResponse> findUserGoalById(@PathVariable Long goalUserId, @AuthenticationPrincipal Long userId) {
            UserGoalDetailGetResponse response = userGoalService.findUserGoalByIdAndUserId(goalUserId, userId);
            return ResponseEntity.ok(response);
        }

        @GetMapping("/apartments")
        public ResponseEntity<List<ApartmentGetResponse>> getAllApartments() {
            List<ApartmentGetResponse> response = apartmentService.getAllApartments();
            return ResponseEntity.ok(response);
        }

        @GetMapping("/cars")
        public ResponseEntity<List<CarGetResponse>> getAllCars() {
            List<CarGetResponse> response = carService.getAllCars();
            return ResponseEntity.ok(response);
        }

        @PostMapping("/apartments/predict")
        public ResponseEntity<ApartmentPricePredictResponse> predictApartmentPrice(@RequestBody ApartmentPricePredictRequest request) {
            ApartmentPricePredictResponse response = apartmentService.predictApartmentPrice(request);
            return ResponseEntity.ok(response);
        }

        @PostMapping
        public ResponseEntity<UserGoalGetResponse> upsertUserGoal(@RequestBody UserGoalUpsertRequest request, @AuthenticationPrincipal Long userId) {
            UserGoalGetResponse response = userGoalService.upsertUserGoal(request, userId);
            return ResponseEntity.ok(response);
        }
    }
