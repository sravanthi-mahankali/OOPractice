package org.example.bank;

import org.example.bank.exception.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.example.bank.Constants.*;

public class Account {

    private static long latestId = 1001;
    private final long id;
    private final String name;
    private AUD balance;
    private final Map<String, List<TransactionDetails>> transactionHistory = new HashMap<>();
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

    public AUD deposit(AUD amount) throws DepositException, MaxTransactionLimitExceededException {
        validateTransaction(TransactionType.DEPOSIT);
        depositValidation(amount);
        balance =  balance.add(amount) ;
        recordTransaction(new TransactionDetails(TransactionType.DEPOSIT, new Date(), amount));
        return balance;
    }

    private void depositValidation(AUD amount) throws DepositException{
        if(amount.lessThan(MIN_DEPOSIT_LIMIT)) {
            throw new MinimumDepositLimitRequiredException();
        }
        if(amount.greaterThan(MAX_DEPOSIT_LIMIT)) {
            throw new DepositLimitExceededException();
        }
        if(balance.add(amount).greaterThan(MAX_BALANCE_LIMIT)) {
            throw new MaxBalanceLimitExceeded();
        }
    }

    public AUD withDraw(AUD amount) throws WithDrawException, MaxTransactionLimitExceededException {
        validateTransaction(TransactionType.WITHDRAW);
        withDrawValidation(amount);
        balance = balance.Subtract(amount);
        recordTransaction(new TransactionDetails(TransactionType.WITHDRAW, new Date(), amount));
        return balance;
    }

    private void withDrawValidation(AUD amount) throws WithDrawException {
        if(amount.lessThan(MIN_WITH_DRAW_LIMIT)) {
            throw new MinimumWithDrawAmountRequiredException();
        }
        if(amount.greaterThan(MAX_WITH_DRAW_LIMIT)) {
            throw new WithDrawLimitExceededException();
        }
        if (balance.lessThan(amount)){
            throw new InsufficientBalanceException();
        }
    }

    public String transfer(AUD amount, Account toAccount) throws TransferException, MaxTransactionLimitExceededException {
        try {
            validateTransaction(TransactionType.TRANSFER);
            this.withDrawValidation(amount);
            toAccount.depositValidation(amount);
            this.balance = balance.Subtract(amount);
            toAccount.balance = toAccount.balance.add(amount);
            recordTransaction(new TransactionDetails(TransactionType.TRANSFER, new Date(), amount));
            return "Successful";
        } catch (WithDrawException exception) {
            throw new TransferException(exception + " for account "+ this.id);
        } catch ( DepositException exception) {
            throw new TransferException(exception + " for account "+ toAccount.id);
        }

    }

    public String getTransactionHistory() {
        return transactionHistory.toString();
    }

    private void validateTransaction(TransactionType transaction) throws MaxTransactionLimitExceededException {
        List<TransactionDetails> todayTransactions = transactionHistory.get(dateFormat.format(new Date()));
        if(todayTransactions != null) {
            if(todayTransactions.stream().filter(t -> t.transactionType == transaction).count() >= MAX_NO_OF_SIMILAR_TRANSACTIONS_PER_DAY) {
                throw new MaxTransactionLimitExceededException(transaction.toString());
            }
        }
    }

    private void recordTransaction(TransactionDetails transactionDetails) {
        String date = dateFormat.format(new Date());
        if(!transactionHistory.containsKey(date)) {
            transactionHistory.put(date, new ArrayList<>(Collections.singleton(transactionDetails)));
            return;
        }
        transactionHistory.get(date).add(transactionDetails);
    }

}
