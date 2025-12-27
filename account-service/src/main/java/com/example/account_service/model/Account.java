package com.example.account_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Map JSON "account type" into thí variable
    @JsonProperty("account type")
    private String accountType;

    // Map JSON "acc number"
    @JsonProperty("acc number")
    private String accNumber;

    // Map JSON "account name"
    @JsonProperty("account name")
    private String accountName;

    // Map JSON "Balance"
    private BigDecimal balance;

    private LocalDate date;
}
