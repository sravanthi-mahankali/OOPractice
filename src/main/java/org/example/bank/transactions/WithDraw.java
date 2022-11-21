package org.example.bank.transactions;

import org.example.bank.AUD;
import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.exception.*;

public class WithDraw implements Transaction {
    private final long accountId;
    private final AUD amount;

    public WithDraw(long accountId, AUD amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    @Override
    public String execute(Bank bank) throws  MaxLimitExceededException, AccountNotFoundException, MinimumWithDrawAmountRequiredException, InsufficientBalanceException, WithDrawLimitExceededException {
        Account account = bank.searchAccount(accountId);
        return String.valueOf(account.withDraw(amount));
    }
}
