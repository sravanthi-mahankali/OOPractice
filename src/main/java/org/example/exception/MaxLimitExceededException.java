package org.example.exception;

public class MaxLimitExceededException extends Exception {
    public MaxLimitExceededException(String message) {
        super(message);
    }
    public String toString(){
        return "Max no of"+ getMessage() +" transaction exceeded" ;
    }
}
