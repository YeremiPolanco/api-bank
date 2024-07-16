package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
import jakarta.servlet.http.PushBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository repository;

    public Account save(Account account) {
        return repository.save(account);
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Account findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Account update(Long id, Account account) {
        Account accountExisting = repository.findById(id).orElse(null);

        accountExisting.setAccountHolderName(account.getAccountHolderName());
        accountExisting.setBalance(account.getBalance());
        return repository.save(accountExisting);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void deposit(Long id, double amount) {
        Account accountExisting = findById(id);
        accountExisting.setBalance(accountExisting.getBalance() + amount);
        save(accountExisting);
    }

    public String withdrawl(Long id, double amount) {
        Account accountExisting = findById(id);
        if (accountExisting.getBalance() - amount >= 0) {
            accountExisting.setBalance(accountExisting.getBalance() - amount);
            save(accountExisting);
            return "Account withdrawl successful";
        }
        else {
            return "Account withdrawl failed";
        }

    }
}
