package com.example.banking.service;

import com.example.banking.model.Account;
import com.example.banking.repository.AccountRepository;
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
}
