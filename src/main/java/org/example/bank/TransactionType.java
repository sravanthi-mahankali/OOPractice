package org.example.bank;

public enum TransactionType {
    DEPOSIT {
        public String toString() {
            return "deposits";
        }
    },
    WITHDRAW {
        public String toString() {
            return "withdrawals";
        }
    },
    CREATE,
    TRANSFER {
        public String toString() {
            return "transfers";
        }
    },

    BALANCE,
    TRANSACTIONHISTORY,
}

