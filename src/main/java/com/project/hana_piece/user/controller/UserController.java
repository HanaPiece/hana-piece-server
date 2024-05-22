package com.project.hana_piece.user.controller;

import com.project.hana_piece.user.dto.UserGetResponse;
import com.project.hana_piece.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    private ResponseEntity<UserGetResponse> findById(@PathVariable Long userId) {
        UserGetResponse response = userService.findByUserId(userId);
        return ResponseEntity.ok(response);
    }
}
