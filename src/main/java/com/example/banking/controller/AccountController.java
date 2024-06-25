package com.example.banking.controller;

import com.example.banking.controller.dto.DepositDto;
import com.example.banking.model.Account;
import com.example.banking.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Account> createAnccount(@RequestBody Account account) {
        Account createdAccount = service.save(account);

        return ResponseEntity.status(201).body(createdAccount);
    }

    @PutMapping("/{id}")
    public Account updateAnccount(@PathVariable Long id, @RequestBody Account account) {
        return service.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAnccountById(@PathVariable Long id) {
        service.delete(id);
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity<String> deposit(@PathVariable Long id, @RequestBody DepositDto depositDto) {
        Account account = service.findById(id);

        if(account == null) {
            return ResponseEntity.notFound().build();
        }
        service.deposit(id, depositDto.getAmount());

        String responsTxt = "Deposited successfully";
        return ResponseEntity.ok(responsTxt);
   }
}
