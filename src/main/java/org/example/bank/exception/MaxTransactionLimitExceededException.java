package org.example.bank.exception;

import static org.example.bank.Constants.MAX_NO_OF_SIMILAR_TRANSACTIONS_PER_DAY;

public class MaxTransactionLimitExceededException extends Exception {
    public MaxTransactionLimitExceededException(String message) {
        super(message);
    }
    public String toString(){
        return "Only "+ MAX_NO_OF_SIMILAR_TRANSACTIONS_PER_DAY +" "+ getMessage() +" are allowed in a day" ;
    }
}
