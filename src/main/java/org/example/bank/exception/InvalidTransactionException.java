package org.example.bank.exception;

import static org.example.bank.Constants.MAX_BALANCE_LIMIT;

public class InvalidTransactionException extends Exception {
    public String toString(){
        return "Invalid transaction";
    }
}
