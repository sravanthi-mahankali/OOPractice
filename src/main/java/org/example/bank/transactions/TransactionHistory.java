package org.example.bank.transactions;

import org.example.bank.AUD;
import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.exception.MaxTransactionLimitExceededException;
import org.example.bank.exception.TransferException;

public class TransactionHistory implements Transaction {
    private final long fromAccountId;


    public TransactionHistory(long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    @Override
    public String execute(Bank bank) throws TransferException, MaxTransactionLimitExceededException, AccountNotFoundException {
        Account fromAccount = bank.searchAccount(fromAccountId);
        return String.valueOf(fromAccount.getTransactionHistory());
    }
}
