package org.example.bank.exception;


public class InsufficientBalanceException extends WithDrawException {
    public String toString(){
        return "Insufficient balance";
    }
}
