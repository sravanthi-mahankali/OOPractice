package org.example.bank.exception;

import static org.example.bank.Constants.MIN_WITH_DRAW_LIMIT;

public class MinimumWithDrawAmountRequiredException extends WithDrawException {
    public String toString(){
        return "Minimum withdrawal amount is " + MIN_WITH_DRAW_LIMIT;
    }
}
