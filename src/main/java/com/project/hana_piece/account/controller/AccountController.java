package com.project.hana_piece.account.controller;

import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountUpsertResponse> saveAccount(@AuthenticationPrincipal Long userId) {
        AccountUpsertResponse response = accountService.saveAccount(userId);
        return ResponseEntity.ok(response);
    }
}
