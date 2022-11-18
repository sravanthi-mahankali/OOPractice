package org.example.exception;

import static org.example.Constants.MIN_WITH_DRAW_LIMIT;

public class MaxLimitExceededException extends Exception {
    public MaxLimitExceededException(String message) {
        super(message);
    }
    public String toString(){
        return "Max no of"+ getMessage() +" transaction exceeded" ;
    }
}
