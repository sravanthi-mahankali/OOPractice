package org.example.bank;

import org.example.bank.exception.*;
import org.example.bank.transactions.Transaction;
import org.example.bank.parser.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public String executeTransaction(String instructions) {
        try {
            Transaction transaction = Parser.convertInputIntoCommands(instructions);
            return transaction.execute(this);
        } catch (Exception exception) {
            return exception.toString();
        }

    }

    public long createAccount(String name) {
        Account newAccount = new Account(name);
        this.accounts.add(newAccount);
        return newAccount.getId();
    }

    public Account searchAccount(long accountId) throws AccountNotFoundException {
        Optional<Account> optionalAccount = accounts.stream().filter(a -> a.getId() == accountId).findAny();
        if(optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        throw new AccountNotFoundException();
    }

}
