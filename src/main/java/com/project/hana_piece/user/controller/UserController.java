package com.project.hana_piece.user.controller;

import com.project.hana_piece.user.dto.*;
import com.project.hana_piece.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserGetResponse> findById(@AuthenticationPrincipal Long userId) {
        UserGetResponse response = userService.findByUserId(userId);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        UserLoginResponse response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/salary")
    public ResponseEntity<UserSalaryUpsertResponse> saveSalary(@AuthenticationPrincipal Long userId, @RequestBody UserSalaryUpsertRequest salaryRequest) {
        UserSalaryUpsertResponse response = userService.upsertUserSalary(userId, salaryRequest.newSalary(), salaryRequest.newSalaryDay());
        return ResponseEntity.ok(response);
    }
}