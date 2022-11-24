package org.example.bank.parser;

import org.example.bank.transactions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void ShouldThrowExceptionWhenSufficientArgsNotProvided(){
        assertThrows(IllegalArgumentException.class, () -> Parser.convertInputIntoCommands("Deposit 2001"));
    }

    @Test
    void ShouldThrowExceptionWhenValidInputNotProvided(){
        assertThrows(IllegalArgumentException.class, () ->  Parser.convertInputIntoCommands("transfer abcd 5000"));
    }

    @Test
    void shouldReturnCreateTransaction()  {
        Transaction transaction = Parser.convertInputIntoCommands("Create Steve Rogers");
        assertTrue(transaction instanceof Create);
    }

    @Test
    void shouldReturnDepositTransaction()  {
        Transaction transaction = Parser.convertInputIntoCommands("Deposit 1001 500");
        assertTrue(transaction instanceof Deposit);
    }

    @Test
    void shouldReturnWithDrawTransaction()  {
        Transaction transaction = Parser.convertInputIntoCommands("Withdraw 1001 500");
        assertTrue(transaction instanceof WithDraw);
    }

    @Test
    void shouldReturnBalanceTransaction()  {
        Transaction transaction = Parser.convertInputIntoCommands("Balance 1001");
        assertTrue(transaction instanceof Balance);
    }

    @Test
    void shouldReturnTransferTransaction()  {
        Transaction transaction = Parser.convertInputIntoCommands("Transfer 1001 1002 5000");
        assertTrue(transaction instanceof Transfer);
    }

    @Test
    void ShouldThrowExceptionWhenInvalidTransactionProvided(){
        assertThrows(IllegalArgumentException.class, () ->  Parser.convertInputIntoCommands("invalid abcd 5000"));
    }
}
