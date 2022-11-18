package org.example;

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

    public long getId() {
        return id;
    }

    public AUD getBalance() {
        return balance;
    }

    public void deposit(AUD amount) {
        balance = balance.add(amount);
    }

    public void withDraw(AUD amount) throws InsufficientBalanceException {
        if (!balance.greaterThanOrEqual(amount)){
            throw new InsufficientBalanceException();
        }
        balance = balance.Subtract(amount);
    }

}
