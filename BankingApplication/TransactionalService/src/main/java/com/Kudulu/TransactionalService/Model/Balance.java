package com.Kudulu.TransactionalService.Model;

import org.springframework.stereotype.Component;

@Component
public class Balance {
    private Double balance;

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
