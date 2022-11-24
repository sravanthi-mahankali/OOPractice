package org.example.bank.transactions;

import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.exception.DepositException;
import org.example.bank.exception.MaxTransactionLimitExceededException;
import org.junit.jupiter.api.Test;

import static org.example.bank.Constants.MIN_DEPOSIT_LIMIT;
import static org.mockito.Mockito.*;

public class DepositTest {
    private Bank mockBank = mock(Bank.class);
    private Account mockAccount =  mock(Account.class);
    @Test
    void ShouldBeAbleToExecuteDepositTransaction() throws DepositException, MaxTransactionLimitExceededException, AccountNotFoundException {
        when(mockBank.searchAccount(1001)).thenReturn(mockAccount);
        Deposit deposit = new Deposit(1001, MIN_DEPOSIT_LIMIT);
        deposit.execute(mockBank);
        verify(mockAccount, times(1)).deposit(MIN_DEPOSIT_LIMIT);
    }
}
