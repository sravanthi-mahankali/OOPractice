package org.example.exception;

import static org.example.Constants.MIN_WITH_DRAW_LIMIT;

public class MinimumWithDrawAmountRequiredException extends Exception {
    public String toString(){
        return "Minimum With Draw Limit Required" + MIN_WITH_DRAW_LIMIT;
    }
}
