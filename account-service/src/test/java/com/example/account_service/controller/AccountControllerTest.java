package com.example.account_service.controller;

import com.example.account_service.dao.AccountRepository;
import com.example.account_service.model.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(AccountController.class)
public class AccountControllerTest{
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AccountRepository repository;

    // Test 1: Get All Accounts
    @Test
    public void testGetAllAccounts() throws Exception {
        Account a1 = new Account(1L, "Saving", "123456", "John", new BigDecimal("500"), LocalDate.now());

        Mockito.when(repository.findAll()).thenReturn(Arrays.asList(a1));

        mockMvc.perform(get("/accounts/account"))
                .andExpect(status().isOk())
                // SỬA: Dùng $['account name'] thay vì $.accountName
                .andExpect(jsonPath("$[0]['account name']").value("John"));
    }

    // Test 2: Get Account By ID
    @Test
    public void testGetAccountById() throws Exception {
        Account a1 = new Account(1L, "Saving", "123456", "John", new BigDecimal("500"), LocalDate.now());

        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(a1));

        mockMvc.perform(get("/account/account/1"))
                .andExpect(status().isOk())
                // SỬA: Dùng $['account name'] thay vì $.accountName
                .andExpect(jsonPath("$['account name']").value("John"));
    }

    // Test 3: Create Account
    @Test
    public void testCreateAccount() throws Exception {
        Account a1 = new Account(1L, "Saving", "123456", "John", new BigDecimal("500"), LocalDate.now());

        Mockito.when(repository.save(Mockito.any(Account.class))).thenReturn(a1);

        String jsonContent = "{\"account type\": \"Saving\", \"acc number\": \"123456\", \"account name\": \"John\", \"Balance\": 500, \"date\": \"2023-10-25\"}";

        mockMvc.perform(post("/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk());
    }
}
