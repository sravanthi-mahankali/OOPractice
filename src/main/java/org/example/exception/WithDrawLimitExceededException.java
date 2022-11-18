package org.example.exception;

import static org.example.Constants.MAX_WITH_DRAW_LIMIT;

public class WithDrawLimitExceededException extends  Exception {
    public String toString(){
        return "Maximum With Draw Limit Exceeded" + MAX_WITH_DRAW_LIMIT;
    }
}
