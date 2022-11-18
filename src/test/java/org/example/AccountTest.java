package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void twoAccountsShouldNotHaveSameID() {
        Account accountOne = new Account("Name1");
        Account accountTwo = new Account("Name2");

        long accountOneId = accountOne.getId();
        long accountTwoId = accountTwo.getId();

        assertNotEquals(accountOneId, accountTwoId);
    }

    @Test
    void ShouldBeAbleToGetBalance() {
        Account account = new Account("Name1");
        AUD balanceAUD = account.getBalance();
        assertEquals(new AUD(0), balanceAUD);

    }

    @Test
    void ShouldBeAbleToUpdateTheBalanceWhenDepositedAmount() {
        Account account = new Account("Name1");
        String balance = account.deposit(new AUD(10));
        assertEquals(new AUD(10), account.getBalance());

    }

    @Test
    void ShouldBeAbleToUpdateTheBalanceAfterWithDrawTheAmount() {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        String balance = account.withDraw(new AUD(5));

        assertEquals("AUD{value=5.0}", balance);
    }

    @Test
    void ShouldNotBeAbleToWithDrawTheAmountGreaterThanBalance() {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        String result = account.withDraw(new AUD(15));

        assertEquals("Insufficient balance", result);
    }

    @Test
    void ShouldBeAbleToWithDrawTotalBalanceAmount() {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        String balance = account.withDraw(new AUD(10));

        assertEquals("AUD{value=0.0}", balance);
    }
}