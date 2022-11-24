package org.example.bank.exception;


import static org.example.bank.Constants.MAX_DEPOSIT_LIMIT;

public class DepositLimitExceededException extends DepositException {
    public String toString(){
        return "Maximum deposit amount is " + MAX_DEPOSIT_LIMIT;
    }
}
