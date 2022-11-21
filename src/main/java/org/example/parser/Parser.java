package org.example.parser;

import org.example.bank.AUD;
import org.example.bank.TransactionTypes;
import org.example.bank.transactions.*;

public class Parser {
    public static Transaction convertInputIntoCommands(String input) {
        String[] args = input.split(" ");
        return switch (TransactionTypes.valueOf(args[0])) {
            case DEPOSIT -> new Deposit(Long.parseLong(args[1]), new AUD(Double.parseDouble(args[2])));
            case WITHDRAW -> new WithDraw(Long.parseLong(args[1]), new AUD(Double.parseDouble(args[2])));
            case TRANSFER -> new Transfer(Long.parseLong(args[1]), Long.parseLong(args[2]), new AUD(Double.parseDouble(args[3])));
            case CREATE -> new Create(args[1]);
            default -> throw new IllegalArgumentException("Invalid Input");
        };
    }
}
