package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.StatementEntry;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
    public class AccountController {
    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    public Account createAccount() {
        return service.createAccount();
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestParam double amount) {
        return ResponseEntity.ok(service.deposit(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestParam double amount) {
        return ResponseEntity.ok(service.withdraw(id, amount));
    }

    @GetMapping("/{id}/statement")
    public List<StatementEntry> statement(@PathVariable Long id) {
        return service.getStatement(id);
    }
}