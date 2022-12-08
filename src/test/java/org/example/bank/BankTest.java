package org.example.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankTest {
    private Bank bank;

    @Test
    void ShouldCreateAccount() throws Exception {
        bank = new Bank();
        String accountId = bank.executeTransaction("Create UserName");
        assertEquals(bank.searchAccount(Long.parseLong(accountId)).getId(), Long.parseLong(accountId));
    }

    @Test
    void ShouldBeAbleToExecuteDepositTransaction() {
        bank = new Bank();
        bank.executeTransaction("Create UserName");
        String amountDeposited = bank.executeTransaction("Deposit 1001 50000");
        assertEquals("50000.0", amountDeposited);
    }

    @Test
    void ShouldBeAbleToExecuteWithDrawTransaction() {
        bank = new Bank();
        String accountId = bank.executeTransaction("Create UserName");
        bank.executeTransaction("Deposit "+accountId+" 50000");
        String balanceAmount = bank.executeTransaction("WithDraw "+accountId+" 5000");
        assertEquals("45000.0", balanceAmount);
    }

    @Test
    void ShouldBeAbleToExecuteTransferTransaction() {
        bank = new Bank();
        String accountOne = bank.executeTransaction("Create UserName1");
        String accountTwo = bank.executeTransaction("Create UserName2");
        bank.executeTransaction("Deposit " + accountOne +" 50000");
        bank.executeTransaction("Transfer " + accountOne +" " + accountTwo+ " 5000");
        assertEquals("45000.0", bank.executeTransaction("Balance "+ accountOne));
        assertEquals("5000.0", bank.executeTransaction("Balance "+ accountTwo));
    }

    @Test
    void ShouldThrowExceptionWhenAccountNotFound(){
        bank = new Bank();
        assertEquals("Account not found", bank.executeTransaction("Deposit 2001 5000"));
    }

}