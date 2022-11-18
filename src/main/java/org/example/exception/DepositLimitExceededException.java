package org.example.exception;


import static org.example.Constants.MAX_DEPOSIT_LIMIT;

public class DepositLimitExceededException extends Exception {
    public String toString(){
        return "Maximum Deposit Limit Exceeded" + MAX_DEPOSIT_LIMIT;
    }
}
