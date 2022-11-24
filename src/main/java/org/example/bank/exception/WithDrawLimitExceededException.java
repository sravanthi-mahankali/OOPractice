package org.example.bank.exception;

import static org.example.bank.Constants.MAX_WITH_DRAW_LIMIT;

public class WithDrawLimitExceededException extends  WithDrawException {
    public String toString(){
        return "Maximum withdrawal amount is " + MAX_WITH_DRAW_LIMIT;
    }
}
