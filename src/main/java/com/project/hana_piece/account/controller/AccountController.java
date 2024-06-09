package com.project.hana_piece.account.controller;

import com.project.hana_piece.account.dto.*;
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
    public ResponseEntity<AccountMonthTransactionGetResponse> findAccountMonthTransactionList(@AuthenticationPrincipal Long userId, @PathVariable Long accountId, @RequestParam("transactionYearMonth") Integer transactionYearMonth) {
        AccountMonthTransactionGetResponse response = accountService.findAccountMonthTransactionList(userId, accountId, transactionYearMonth);
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

    @GetMapping("/auto-debit/adjust")
    public ResponseEntity<List<AccountAutoDebitAdjustGetResponse>> findAccountAutoDebitAdjust(@AuthenticationPrincipal Long userId) {
        List<AccountAutoDebitAdjustGetResponse> response = accountService.findAccountAutoDebitAdjust(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/auto-debit/suggestions/{type}")
    public ResponseEntity<AccountAutoDebitSuggestGetResponse> findAccountAutoDebitSuggest(@AuthenticationPrincipal Long userId, @PathVariable String type){
        AccountAutoDebitSuggestGetResponse response = accountService.findAccountAutoDebitSuggest(userId, type);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/auto-debit/adjust")
    public ResponseEntity<Void> updateAccountAutoDebitAdjust(@RequestBody AccountAutoDebitAdjustUpsertRequest request) {
        accountService.updateAccountAutoDebitAdjust(request);
        return ResponseEntity.noContent().build();
    }
}
