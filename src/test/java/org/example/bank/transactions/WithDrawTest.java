package org.example.bank.transactions;

import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.exception.MaxTransactionLimitExceededException;
import org.example.bank.exception.WithDrawException;
import org.junit.jupiter.api.Test;

import static org.example.bank.Constants.MIN_WITH_DRAW_LIMIT;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class WithDrawTest {
    private Bank mockBank = mock(Bank.class);
    private Account mockAccount =  mock(Account.class);
    @Test
    void ShouldBeAbleToExecuteDepositTransaction() throws AccountNotFoundException, MaxTransactionLimitExceededException, WithDrawException {
        when(mockBank.searchAccount(1001)).thenReturn(mockAccount);
        WithDraw withDraw = new WithDraw(1001, MIN_WITH_DRAW_LIMIT);
        withDraw.execute(mockBank);
        verify(mockAccount, times(1)).withDraw(MIN_WITH_DRAW_LIMIT);
    }
}
