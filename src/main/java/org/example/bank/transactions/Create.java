package org.example.bank.transactions;

import org.example.bank.Bank;

public class Create implements Transaction {

    private final String name;

    public Create(String name) {
        this.name = name;
    }

    @Override
    public String execute(Bank bank) {
        return String.valueOf(bank.createAccount(name));
    }
}
