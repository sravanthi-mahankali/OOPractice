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
        account.deposit(new AUD(10));
        assertEquals(new AUD(10), account.getBalance());

    }

    @Test
    void ShouldBeAbleToUpdateTheBalanceAfterWithDrawTheAmount() throws InsufficientBalanceException {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        account.withDraw(new AUD(5));

        assertEquals(new AUD(5), account.getBalance());
    }

    @Test
    void ShouldNotBeAbleToWithDrawTheAmountGreaterThanBalance() throws InsufficientBalanceException {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        assertThrows(InsufficientBalanceException.class, () ->account.withDraw(new AUD(15)));
        assertEquals(new AUD(10), account.getBalance());
    }

    @Test
    void ShouldBeAbleToWithDrawTotalBalanceAmount() throws InsufficientBalanceException {
        Account account = new Account("Name1");
        account.deposit(new AUD(10));

        account.withDraw(new AUD(10));

        assertEquals(new AUD(0), account.getBalance());
    }
}