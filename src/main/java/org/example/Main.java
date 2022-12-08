package org.example;

import org.example.bank.Bank;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        System.out.println(bank.executeTransaction("Create Steve Rogers"));
        System.out.println(bank.executeTransaction("Deposit 1001 500"));
        System.out.println(bank.executeTransaction("Deposit 1001 1000"));
        System.out.println(bank.executeTransaction("Balance 1001"));
        System.out.println(bank.executeTransaction("Withdraw 1001 500"));
        System.out.println(bank.executeTransaction("TransactionHistory 1001"));
    }
}

/**
 *  assertEquals(bank.executeTransaction("Create Steve Rogers"), "1001");
 *         assertEquals(bank.executeTransaction("Create Diana Prince"), "1002");
 *         assertEquals(bank.executeTransaction("Deposit 1001 500"), "500.0");
 *         assertEquals(bank.executeTransaction("Deposit 1001 1000"), "1500.0");
 *         assertEquals(bank.executeTransaction("Deposit 1001 100"), "Minimum deposit amount is 500.0");
 *         assertEquals(bank.executeTransaction("Deposit 1001 60000"), "Maximum deposit amount is 50000.0");
 *         assertEquals(bank.executeTransaction("Deposit 1001 10000"), "11500.0");
 *         assertEquals(bank.executeTransaction("Deposit 1001 10000"), "Only 3 deposits are allowed in a day");
 *         assertEquals(bank.executeTransaction("Balance 1001"), "11500.0");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 500"), "Minimum withdrawal amount is 1000.0");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 20000"), "Insufficient balance");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 1000"), "10500.0");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 1900"), "8600.0");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 1000"), "7600.0");
 *         assertEquals(bank.executeTransaction("Withdraw 1001 5000"), "Only 3 withdrawals are allowed in a day");
 *         assertEquals(bank.executeTransaction("Transfer 1001 1002 5000"), "Successful");
 *         assertEquals(bank.executeTransaction("Create Diana star"), "1003");
 *         assertEquals(bank.executeTransaction("Transfer 1002 1003 500"), "Minimum withdrawal amount is 1000.0 for account 1002");
 *         assertEquals(bank.executeTransaction("Transfer 1002 1003 30000"), "Maximum withdrawal amount is 25000.0 for account 1002");
 * */