package org.example;

public class InsufficientBalanceException extends Exception{
    public String toString(){
        return "Insufficient balance";
    }
}
