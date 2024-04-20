package TheBankingSystem;

import java.util.*;

// Класс для описания транзакций
public class Transaction {
    private Account fromAccount;
    private Account toAccount;
    private double amount;
    private String date;
    private String transactionType;

    public Transaction(Account fromAccount, Account toAccount, double amount, String date, String transactionType) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.date = date;
        this.transactionType = transactionType;
    }

    public boolean execute() {
        switch (transactionType) {
            case "TRANSFER":
                if (fromAccount.withdraw(amount)) {
                    toAccount.deposit(amount);
                    return true;
                }
                return false;
            case "DEPOSIT":
                toAccount.deposit(amount);
                return true;
            case "WITHDRAWAL":
                return fromAccount.withdraw(amount);
            default:
                return false;
        }
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getTransactionType() {
        return transactionType;
    }
}