package org.example.bank.transactions;

import org.example.bank.Bank;
import org.example.exception.*;

public interface Transaction {
    String execute(Bank bank) throws DepositLimitExceededException, MaxBalanceLimitExceeded, MaxLimitExceededException, AccountNotFoundException, MinimumDepositLimitRequiredException, MinimumWithDrawAmountRequiredException, InsufficientBalanceException, WithDrawLimitExceededException;
}
