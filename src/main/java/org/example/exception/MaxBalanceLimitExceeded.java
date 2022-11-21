package org.example.exception;

import static org.example.bank.Constants.MAX_BALANCE_LIMIT;

public class MaxBalanceLimitExceeded extends Exception {
    public String toString(){
        return "Maximum Balance Limit Exceeded" + MAX_BALANCE_LIMIT;
    }
}
