package org.example.bank.parser;

import org.example.bank.AUD;
import org.example.bank.TransactionType;
import org.example.bank.exception.InvalidTransactionException;
import org.example.bank.transactions.*;

import java.util.Locale;

public class Parser {
    public static Transaction convertInputIntoCommands(String input) {
        try {
            String[] args = input.split(" ");
            TransactionType transactionType = TransactionType.valueOf(args[0].toUpperCase(Locale.ROOT));
            return switch (transactionType) {
                case DEPOSIT -> new Deposit(Long.parseLong(args[1]), new AUD(Double.parseDouble(args[2])));
                case WITHDRAW -> new WithDraw(Long.parseLong(args[1]), new AUD(Double.parseDouble(args[2])));
                case TRANSFER -> new Transfer(Long.parseLong(args[1]), Long.parseLong(args[2]), new AUD(Double.parseDouble(args[3])));
                case CREATE -> new Create(args[1]);
                case BALANCE -> new Balance(Long.parseLong(args[1]));
            };
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException exception) {
           throw new IllegalArgumentException("Invalid Input");
        }
    }
}
