package org.example.bank.transactions;

import org.example.bank.Account;
import org.example.bank.Bank;
import org.junit.jupiter.api.Test;

import static org.example.bank.Constants.MIN_DEPOSIT_LIMIT;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class BalanceTest {
    private Bank mockBank = mock(Bank.class);
    private Account mockAccount =  mock(Account.class);
    @Test
    void ShouldBeAbleToExecuteDepositTransaction() throws Exception {
        when(mockBank.searchAccount(1001)).thenReturn(mockAccount);
        Balance balance = new Balance(1001);
        balance.execute(mockBank);
        verify(mockAccount, times(1)).getBalance();
    }
}
