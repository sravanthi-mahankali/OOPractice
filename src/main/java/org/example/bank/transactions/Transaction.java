package org.example.bank.transactions;

import org.example.bank.Bank;
import org.example.bank.exception.*;

public interface Transaction {
    String execute(Bank bank) throws DepositException, MaxTransactionLimitExceededException, AccountNotFoundException, WithDrawException, TransferException;
}
