package org.example.bank.transactions;

import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.bank.exception.AccountNotFoundException;

public class Balance implements Transaction {

    private final long accountId;

    public Balance(long accountId) {
        this.accountId = accountId;
    }

    @Override
    public String execute(Bank bank) throws AccountNotFoundException {
        Account account = bank.searchAccount(accountId);
        return String.valueOf(account.getBalance());
    }
}