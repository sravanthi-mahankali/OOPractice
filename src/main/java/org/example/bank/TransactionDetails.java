package org.example.bank;

import java.util.Date;

public class TransactionDetails {
    public TransactionType transactionType;
    private Date date;
    private AUD aud;

    public TransactionDetails(TransactionType transactionType, Date date, AUD aud) {
        this.transactionType = transactionType;
        this.date = date;
        this.aud = aud;
    }

    @Override
    public String toString() {
        return "TransactionDetails{" +
                "transactionType=" + transactionType +
                ", date=" + date +
                ", aud=" + aud +
                '}';
    }
}
