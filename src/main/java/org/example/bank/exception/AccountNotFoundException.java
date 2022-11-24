package org.example.bank.exception;

import static org.example.bank.Constants.MAX_DEPOSIT_LIMIT;

public class AccountNotFoundException extends Exception {
    public String toString(){
        return "Account not found";
    }
}
