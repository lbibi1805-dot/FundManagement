package com.example.account_service.controller;

import com.example.account_service.dao.AccountRepository;
import com.example.account_service.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository repository;

    // 1. Create Account (POST/account)
    @PostMapping("/account")
    public Account createAccount(@RequestBody Account account){
        return repository.save(account);
    }

    // 2. Get 1 account:
    @GetMapping("/account/account/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id){
        Optional<Account> account = repository.findById(id);
        return account.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 3. Get the full list of accounts:
    @GetMapping("/accounts/account")
    public List<Account> getAllAccounts(){
        return repository.findAll();
    }

    // 4. Update (Put Mapping):
    @PutMapping("/accounts/account")
    public Account updateAccount(@RequestBody Account account){
        return repository.save(account);
    }

    // 5. DELETE
    @DeleteMapping("/accounts/account")
    public void deleteAccount(@RequestBody Account account){
        repository.delete(account);
    }
}
