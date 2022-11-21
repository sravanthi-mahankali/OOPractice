package org.example.bank.transactions;

import org.example.bank.AUD;
import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.exception.*;

public class Transfer implements Transaction {
    private final long fromAccountId;
    private final long toAccountId;
    private final AUD amount;

    public Transfer(long fromAccountId, long toAccountId, AUD amount) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    @Override
    public String execute(Bank bank) throws DepositLimitExceededException, MaxBalanceLimitExceeded, MaxLimitExceededException, AccountNotFoundException, MinimumDepositLimitRequiredException, MinimumWithDrawAmountRequiredException, InsufficientBalanceException, WithDrawLimitExceededException {
        Account fromAccount = bank.searchAccount(fromAccountId);
        Account toAccount = bank.searchAccount(toAccountId);
        return String.valueOf(fromAccount.transfer(amount, toAccount));
    }
}
