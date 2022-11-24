package org.example.bank.transactions;

import org.example.bank.Account;
import org.example.bank.Bank;
import org.example.bank.exception.AccountNotFoundException;
import org.example.bank.exception.MaxTransactionLimitExceededException;
import org.example.bank.exception.TransferException;
import org.junit.jupiter.api.Test;

import static org.example.bank.Constants.MIN_DEPOSIT_LIMIT;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

public class TransferTest {
    private Bank mockBank = mock(Bank.class);

    @Test
    void ShouldBeAbleToExecuteTransferTransaction() throws AccountNotFoundException, TransferException, MaxTransactionLimitExceededException {
        Account mockFromAccount = mock(Account.class);
        Account mockToAccount = mock(Account.class);
        when(mockBank.searchAccount(1001)).thenReturn(mockFromAccount);
        when(mockBank.searchAccount(1002)).thenReturn(mockToAccount);
        Transfer transfer = new Transfer(1001, 1002, MIN_DEPOSIT_LIMIT);
        transfer.execute(mockBank);
        verify(mockFromAccount, times(1)).transfer(MIN_DEPOSIT_LIMIT, mockToAccount);
    }
}
