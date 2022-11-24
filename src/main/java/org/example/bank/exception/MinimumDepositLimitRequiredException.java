package org.example.bank.exception;

import static org.example.bank.Constants.MIN_DEPOSIT_LIMIT;

public class MinimumDepositLimitRequiredException extends DepositException {
    public String toString(){
        return "Minimum deposit amount is " + MIN_DEPOSIT_LIMIT;
    }
}
