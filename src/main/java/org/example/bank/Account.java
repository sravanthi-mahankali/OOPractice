package org.example.bank;

import org.example.exception.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.example.bank.Constants.*;

public class Account {

    private static long latestId = 1001;
    private final long id;
    private final String name;
    private AUD balance;
    private final Map<String, List<TransactionTypes>> transactionHistory = new HashMap<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

    public Account(String name) {
        this.name = name;
        this.balance = new AUD(0);
        id = latestId++;
    }

    public Account(String name, AUD balance) {
        this.name = name;
        this.balance = balance;
        id = latestId++;
    }

    public long getId() {
        return id;
    }

    public AUD getBalance() {
        return balance;
    }

    public AUD deposit(AUD amount) throws MaxBalanceLimitExceeded, DepositLimitExceededException, MinimumDepositLimitRequiredException, MaxLimitExceededException {
        validateTransaction(TransactionTypes.DEPOSIT);
        if(amount.lessThan(MIN_DEPOSIT_LIMIT)) {
            throw new MinimumDepositLimitRequiredException();
        }
        if(amount.greaterThan(MAX_DEPOSIT_LIMIT)) {
            throw new DepositLimitExceededException();
        }
        AUD newBalance = balance.add(amount);
        if(newBalance.greaterThan(MAX_BALANCE_LIMIT)) {
            throw new MaxBalanceLimitExceeded();
        }
        balance = newBalance ;
        recordTransaction(TransactionTypes.DEPOSIT);
        return balance;
    }

    public AUD withDraw(AUD amount) throws InsufficientBalanceException, MinimumWithDrawAmountRequiredException, WithDrawLimitExceededException, MaxLimitExceededException {
        validateTransaction(TransactionTypes.WITHDRAW);
        if(amount.lessThan(MIN_WITH_DRAW_LIMIT)) {
            throw new MinimumWithDrawAmountRequiredException();
        }
        if(amount.greaterThan(MAX_WITH_DRAW_LIMIT)) {
            throw new WithDrawLimitExceededException();
        }
        if (balance.lessThan(amount)){
            throw new InsufficientBalanceException();
        }
        balance = balance.Subtract(amount);
        recordTransaction(TransactionTypes.WITHDRAW);
        return balance;
    }

    public AUD transfer(AUD amount, Account toAccount) throws MinimumWithDrawAmountRequiredException, InsufficientBalanceException, MaxLimitExceededException, WithDrawLimitExceededException, DepositLimitExceededException, MaxBalanceLimitExceeded, MinimumDepositLimitRequiredException {
        this.withDraw(amount);
        toAccount.deposit(amount);
        return balance;
    }

    private void validateTransaction(TransactionTypes transaction) throws MaxLimitExceededException {
        List<TransactionTypes> todayTransactions = transactionHistory.get(dateFormat.format(new Date()));
        if(todayTransactions != null) {
            if(todayTransactions.stream().filter(t -> t == transaction).count() >= MAX_NO_OF_Similar_Transactions_PER_DAY) {
                throw new MaxLimitExceededException(transaction.toString());
            }
        }
    }

    private void recordTransaction(TransactionTypes transactionType) {
        String date = dateFormat.format(new Date());
        if(!transactionHistory.containsKey(date)) {
            transactionHistory.put(date, new ArrayList<>(Collections.singleton(transactionType)));
            return;
        }
        transactionHistory.get(date).add(transactionType);
    }

}
