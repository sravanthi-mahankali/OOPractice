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

    public String deposit(AUD amount) {
        balance = balance.add(amount);
        return balance.toString();
    }

    public String withDraw(AUD amount) {
        if (!balance.greaterThanOrEqual(amount)){
            return "Insufficient balance";
        }
        balance = balance.Subtract(amount);
        return balance.toString();
    }

}
