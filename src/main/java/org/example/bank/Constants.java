package org.example.bank;

public class Constants {
    public static final AUD MAX_BALANCE_LIMIT = new AUD(100000);
    public static final AUD MAX_DEPOSIT_LIMIT = new AUD(50000);
    public static final AUD MIN_DEPOSIT_LIMIT = new AUD(500);

    public static final AUD MAX_WITH_DRAW_LIMIT = new AUD(25000);
    public static final AUD MIN_WITH_DRAW_LIMIT = new AUD(1000);
    public static final Integer MAX_NO_OF_SIMILAR_TRANSACTIONS_PER_DAY = 3;
}
