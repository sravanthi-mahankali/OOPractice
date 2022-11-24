package org.example.bank.exception;

import static org.example.bank.Constants.MAX_NO_OF_SIMILAR_TRANSACTIONS_PER_DAY;
import static org.example.bank.Constants.MIN_WITH_DRAW_LIMIT;

public class TransferException extends Exception{
    public TransferException(String message) {
        super(message);
    }

    public String toString(){
        return getMessage();
    }
}
