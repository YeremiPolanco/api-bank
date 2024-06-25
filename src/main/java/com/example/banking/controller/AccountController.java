package com.example.banking.controller;

import com.example.banking.model.Account;
import com.example.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {
    private final AccountService service;

    @GetMapping
    public List<Account> getAllAnccounts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Account getAnccountById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Account createAnccount(@RequestBody Account account) {
        return service.save(account);
    }

    @PutMapping("/{id}")
    public Account updateAnccount(@PathVariable Long id, @RequestBody Account account) {
        return service.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAnccountById(@PathVariable Long id) {
        service.delete(id);
    }
}
