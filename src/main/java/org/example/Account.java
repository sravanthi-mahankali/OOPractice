package org.example;

import org.example.exception.DepositLimitExceededException;
import org.example.exception.InsufficientBalanceException;
import org.example.exception.MaxBalanceLimitExceeded;
import org.example.exception.MinimumDepositLimitRequiredException;

import static org.example.Constants.*;

public class Account {

    private static long latestId = 1001;
    private final long id;
    private final String name;
    private AUD balance;

    public Account(String name) {
        this.name = name;
        this.balance = new AUD(0);
        id = latestId++;
    }

    public Account(String name, AUD balance) {
        this.name = name;
        this.balance = balance;
        id = latestId++;
    }

    public long getId() {
        return id;
    }

    public AUD getBalance() {
        return balance;
    }

    public void deposit(AUD amount) throws MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException {
        if(amount.lessThan(MIN_DEPOSIT_LIMIT)) {
            throw new MinimumDepositLimitRequiredException();
        }
        if(amount.greaterThan(MAX_DEPOSIT_LIMIT)) {
            throw new DepositLimitExceededException();
        }
        AUD newBalance = balance.add(amount);
        if(newBalance.greaterThan(MAX_BALANCE_LIMIT)) {
            throw new MaxBalanceLimitExceeded();
        }
        balance = newBalance ;
    }

    public void withDraw(AUD amount) throws InsufficientBalanceException {
        if (!balance.greaterThanOrEqual(amount)){
            throw new InsufficientBalanceException();
        }
        balance = balance.Subtract(amount);
    }

}
