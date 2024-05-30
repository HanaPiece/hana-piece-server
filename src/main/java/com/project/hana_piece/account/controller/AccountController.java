package com.project.hana_piece.account.controller;

import com.project.hana_piece.account.dto.AccountGetResponse;
import com.project.hana_piece.account.dto.AccountMonthTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountSalaryGetResponse;
import com.project.hana_piece.account.dto.AccountSavingGetResponse;
import com.project.hana_piece.account.dto.AccountTransactionGetResponse;
import com.project.hana_piece.account.dto.AccountTypeRegRequest;
import com.project.hana_piece.account.dto.AccountUpsertResponse;
import com.project.hana_piece.account.dto.UserGoalAccountGetResponse;
import com.project.hana_piece.account.service.AccountService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/account-type-reg")
    public ResponseEntity<Void> registerAccountType(@AuthenticationPrincipal Long userId, @RequestBody AccountTypeRegRequest accountTypeRegRequest) {
        accountService.registerAccountType(userId, accountTypeRegRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/checking")
    public ResponseEntity<List<AccountGetResponse>> findCheckingAccountList(@AuthenticationPrincipal Long userId) {
        List<AccountGetResponse> response = accountService.findCheckingAccountList(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/installment-saving")
    public ResponseEntity<List<AccountGetResponse>> findSavingAccountList(@AuthenticationPrincipal Long userId) {
        List<AccountGetResponse> response = accountService.findInstallmentSavingAccountList(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/user-goal/{userGoalId}")
    public ResponseEntity<List<UserGoalAccountGetResponse>> findUserGoalAccountList(@AuthenticationPrincipal Long userId, @PathVariable Long userGoalId) {
        List<UserGoalAccountGetResponse> response = accountService.findUserGoalAccountList(userId, userGoalId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}/transactions/goal-installment-saving")
    public ResponseEntity<List<AccountTransactionGetResponse>> findGoalAccountTransactionList(@AuthenticationPrincipal Long userId, @PathVariable Long accountId) {
        List<AccountTransactionGetResponse> response = accountService.findGoalAccountTransactionList(userId, accountId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<AccountMonthTransactionGetResponse> findAccountMonthTransactionList(@AuthenticationPrincipal Long userId, @PathVariable Long accountId, @RequestParam("month") Integer month) {
        AccountMonthTransactionGetResponse response = accountService.findAccountMonthTransactionList(userId, accountId, month);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/salary")
    public ResponseEntity<AccountSalaryGetResponse> findSalaryAccount(@AuthenticationPrincipal Long userId) {
        AccountSalaryGetResponse response = accountService.findSalaryAccount(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/saving")
    public ResponseEntity<AccountSavingGetResponse> findSavingAccount(@AuthenticationPrincipal Long userId) {
        AccountSavingGetResponse response = accountService.findSavingAccount(userId);
        return ResponseEntity.ok(response);
    }
}
