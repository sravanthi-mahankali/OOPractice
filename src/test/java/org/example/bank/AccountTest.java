package org.example.bank;

import org.example.bank.exception.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.example.bank.Constants.*;
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
        void ShouldBeAbleToUpdateTheBalanceWhenDepositedAmount() throws MaxTransactionLimitExceededException, DepositException {
            Account account = new Account("Name1");
            AUD balance = account.deposit(MIN_DEPOSIT_LIMIT);
            assertEquals(MIN_DEPOSIT_LIMIT, balance);
            assertEquals(balance, account.getBalance());
        }

        @Test
        void ShouldThrowMaxBalanceLimitExceededWhenDepositedMoreThanMaxBalanceLimt() {
            Account account = new Account("Name1", MAX_BALANCE_LIMIT);

            assertThrows(MaxBalanceLimitExceeded.class, () -> account.deposit(MIN_DEPOSIT_LIMIT));
            assertEquals(MAX_BALANCE_LIMIT, account.getBalance());
        }

        @Test
        void ShouldThrowMinimumDepositLimitRequiredException() {
            Account account = new Account("Name1");

            assertThrows(MinimumDepositLimitRequiredException.class, () -> account.deposit(MIN_DEPOSIT_LIMIT.Subtract(new AUD(4))));
        }

        @Test
        void ShouldThrowDepositLimitExceeded() {
            Account account = new Account("Name1");

            assertThrows(DepositLimitExceededException.class, () -> account.deposit(MAX_DEPOSIT_LIMIT.add(new AUD(5))));
        }

        @Test
        void ShouldThrowMaxLimitExceededException() throws MaxTransactionLimitExceededException, DepositException {
            Account account = new Account("Name1");
            account.deposit(MIN_DEPOSIT_LIMIT);
            account.deposit(MIN_DEPOSIT_LIMIT);
            account.deposit(MIN_DEPOSIT_LIMIT);

            assertThrows(MaxTransactionLimitExceededException.class, () -> account.deposit(MIN_DEPOSIT_LIMIT));
            assertEquals(new AUD(1500), account.getBalance());
        }

    }

    @Nested
    class WithDraw {

        @Test
        void ShouldBeAbleToUpdateTheBalanceAfterWithDrawTheAmount() throws MaxTransactionLimitExceededException, WithDrawException, DepositException {
            Account account = new Account("Name1");
            account.deposit(new AUD(1200));

            AUD balance = account.withDraw(new AUD(1000));

            assertEquals(new AUD(200), balance);
        }

        @Test
        void ShouldNotBeAbleToWithDrawTheAmountGreaterThanBalance() throws MaxTransactionLimitExceededException, DepositException {
            Account account = new Account("Name1");
            account.deposit(new AUD(1000));

            assertThrows(InsufficientBalanceException.class, () -> account.withDraw(new AUD(1004)));
            assertEquals(new AUD(1000), account.getBalance());
        }

        @Test
        void ShouldBeAbleToWithDrawTotalBalanceAmount() throws MaxTransactionLimitExceededException, DepositException, WithDrawException {
            Account account = new Account("Name1");
            account.deposit(new AUD(1000));

            AUD balance = account.withDraw(new AUD(1000));

            assertEquals(new AUD(0), balance);
        }

        @Test
        void ShouldThrowMinimumWithDrawAmountRequiredException() throws MaxTransactionLimitExceededException, DepositException {
            Account account = new Account("Name1");
            account.deposit(MAX_DEPOSIT_LIMIT);

            assertThrows(MinimumWithDrawAmountRequiredException.class, () -> account.withDraw(MIN_WITH_DRAW_LIMIT.Subtract(new AUD(4))));
        }

        @Test
        void ShouldThrowWithDrawLimitExceededException() throws MaxTransactionLimitExceededException, DepositException {
            Account account = new Account("Name1");
            account.deposit(MAX_DEPOSIT_LIMIT);

            assertThrows(WithDrawLimitExceededException.class, () -> account.withDraw(MAX_WITH_DRAW_LIMIT.add(new AUD(5))));
        }

        @Test
        void ShouldThrowMaxLimitExceededException() throws MaxTransactionLimitExceededException, WithDrawException {
            Account account = new Account("Name1", MAX_BALANCE_LIMIT);
            account.withDraw(MIN_WITH_DRAW_LIMIT);
            account.withDraw(MIN_WITH_DRAW_LIMIT);
            account.withDraw(MIN_WITH_DRAW_LIMIT);

            assertThrows(MaxTransactionLimitExceededException.class, () -> account.withDraw(MIN_WITH_DRAW_LIMIT));
        }
    }

    @Nested
    class transfer {
        @Test
        void ShouldTransferAmountAccountOneToOther() throws MaxTransactionLimitExceededException, TransferException {
            Account accountOne = new Account("user 1", MAX_DEPOSIT_LIMIT);
            Account accountTwo = new Account("user 2");
            AUD thousandAUD = new AUD(1000);
            accountOne.transfer(thousandAUD, accountTwo);
            assertEquals(MAX_DEPOSIT_LIMIT.Subtract(thousandAUD), accountOne.getBalance());
            assertEquals(thousandAUD, accountTwo.getBalance());
        }

        @Test
        void ShouldNotTransferMoreThanPermittedTransactions() throws MaxTransactionLimitExceededException, TransferException {
            Account accountOne = new Account("user 1", MAX_DEPOSIT_LIMIT);
            Account accountTwo = new Account("user 2");
            AUD thousandAUD = new AUD(1000);
            accountOne.transfer(thousandAUD, accountTwo);
            accountOne.transfer(thousandAUD, accountTwo);
            accountOne.transfer(thousandAUD, accountTwo);
            assertThrows(MaxTransactionLimitExceededException.class, () -> accountOne.transfer(thousandAUD, accountTwo));
            assertEquals(new AUD(3000), accountTwo.getBalance());
            assertEquals(MAX_DEPOSIT_LIMIT.Subtract(new AUD(3000)), accountOne.getBalance());
        }
    }
}