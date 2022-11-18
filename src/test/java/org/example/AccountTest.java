package org.example;

import org.example.exception.DepositLimitExceededException;
import org.example.exception.InsufficientBalanceException;
import org.example.exception.MaxBalanceLimitExceeded;
import org.example.exception.MinimumDepositLimitRequiredException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.example.Constants.*;
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

    @Nested
    class Deposit {

        @Test
        void ShouldBeAbleToUpdateTheBalanceWhenDepositedAmount() throws MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException {
            Account account = new Account("Name1");
            account.deposit(new AUD(10));
            assertEquals(new AUD(10), account.getBalance());
        }

        @Test
        void ShouldThrowMaxBalanceLimitExceededWhenDepositedMoreThanMaxBalanceLimt() {
            Account account = new Account("Name1", MAX_BALANCE_LIMIT);

            assertThrows(MaxBalanceLimitExceeded.class, () ->account.deposit(new AUD(15)));
            assertEquals(MAX_BALANCE_LIMIT, account.getBalance());
        }

        @Test
        void ShouldThrowMinimumDepositLimitRequiredException() {
            Account account = new Account("Name1");

            assertThrows(MinimumDepositLimitRequiredException.class, () ->account.deposit(MIN_DEPOSIT_LIMIT.Subtract(new AUD(4))));
        }

        @Test
        void ShouldThrowDepositLimitExceeded() {
            Account account = new Account("Name1");

            assertThrows(DepositLimitExceededException.class, () ->account.deposit(MAX_DEPOSIT_LIMIT.add(new AUD(5))));
        }

    }

    @Nested
    class WithDraw {

        @Test
        void ShouldBeAbleToUpdateTheBalanceAfterWithDrawTheAmount() throws InsufficientBalanceException, MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException {
            Account account = new Account("Name1");
            account.deposit(MIN_DEPOSIT_LIMIT);

            account.withDraw(new AUD(5));

            assertEquals(new AUD(5), account.getBalance());
        }

        @Test
        void ShouldNotBeAbleToWithDrawTheAmountGreaterThanBalance() throws MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException {
            Account account = new Account("Name1");
            account.deposit(MIN_DEPOSIT_LIMIT);

            assertThrows(InsufficientBalanceException.class, () ->account.withDraw(MIN_DEPOSIT_LIMIT.add(new AUD(40))));
            assertEquals(MIN_DEPOSIT_LIMIT, account.getBalance());
        }

        @Test
        void ShouldBeAbleToWithDrawTotalBalanceAmount() throws InsufficientBalanceException, MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException {
            Account account = new Account("Name1");
            account.deposit(new AUD(10));

            account.withDraw(new AUD(10));

            assertEquals(new AUD(0), account.getBalance());
        }
    }
}