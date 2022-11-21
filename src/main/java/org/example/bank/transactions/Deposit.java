package org.example.bank.transactions;

import org.example.bank.AUD;
import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.exception.*;

public class Deposit implements Transaction {
    private final AUD amount;
    private final long accountId;

    public Deposit(long accountId, AUD amount) {
        this.amount = amount;
        this.accountId = accountId;
    }

    @Override
    public String execute(Bank bank) throws DepositLimitExceededException, MaxBalanceLimitExceeded, MaxLimitExceededException, AccountNotFoundException, MinimumDepositLimitRequiredException {
        Account account = bank.searchAccount(accountId);
        return String.valueOf(account.deposit(amount));
    }
}
