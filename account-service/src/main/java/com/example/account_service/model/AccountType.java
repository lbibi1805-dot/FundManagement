package com.example.account_service.model;

public enum AccountType {
    TERM_INVESTMENT("Term Investment"),
    LOAN("Loan"),
    SAVING("Saving");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
