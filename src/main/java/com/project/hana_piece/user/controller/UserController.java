package com.project.hana_piece.user.controller;

import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.dto.UserLoginRequest;
import com.project.hana_piece.user.dto.UserLoginResponse;
import com.project.hana_piece.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    private ResponseEntity<UserGetResponse> findById(@PathVariable Long userId) {
        UserGetResponse response = userService.findByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    private ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest dto) {
        UserLoginResponse response = userService.login(dto);
        return ResponseEntity.ok(response);
    }

    // 월급에 필요한 정보 : 월급계좌 : account_auto_debit.account_id->accounts.account_number / 월급 users.salary / 월급일 : account_auto_debit.auto_debit_day
//    @GetMapping("/salary")
//    public ResponseEntity<UserSalaryGetResponse> getUserSalary(@AuthenticationPrincipal Long userId) {
//        UserSalaryGetResponse response = userService.getUserSalary(userId);
//        return ResponseEntity.ok(response);
//    }
}
