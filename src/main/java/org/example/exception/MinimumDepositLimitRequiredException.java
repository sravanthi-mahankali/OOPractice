package org.example.exception;

import static org.example.bank.Constants.MIN_DEPOSIT_LIMIT;

public class MinimumDepositLimitRequiredException extends Exception {
    public String toString(){
        return "Minimum Deposit Limit Required" + MIN_DEPOSIT_LIMIT;
    }
}
