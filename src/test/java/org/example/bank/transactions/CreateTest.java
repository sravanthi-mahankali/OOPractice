package org.example.bank.transactions;

import org.example.bank.Bank;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class CreateTest {

    private Bank mockBank = mock(Bank.class);

    @Test
    void ShouldBeAbleToExecuteCreateTransaction() {
        Create create = new Create("user1");
        create.execute(mockBank);
        verify(mockBank, times(1)).createAccount("user1");
    }
}
