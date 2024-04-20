package TheBankingSystem;
import java.time.LocalDateTime;

public class Transaction1 {
    private Account fromAccount;  // Счет отправителя
    private Account toAccount;    // Счет получателя
    private double amount;        // Сумма транзакции
    private LocalDateTime dateTime;  // Дата и время транзакции
    private String transactionType;  // Тип транзакции: "DEPOSIT", "WITHDRAWAL", "TRANSFER"

    // Конструктор для транзакции
    public Transaction1(Account fromAccount, Account toAccount, double amount, String transactionType) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.dateTime = LocalDateTime.now();
        this.transactionType = transactionType;
    }

    // Метод для выполнения транзакции
    public boolean execute() {
        System.out.println("Executing transaction of type: " + transactionType + " on " + dateTime.toString());
        switch (transactionType) {
            case "TRANSFER":
                if (fromAccount != null && toAccount != null && fromAccount.withdraw(amount)) {
                    toAccount.deposit(amount);
                    return true;
                }
                return false;
            case "Внесение":
                if (toAccount != null) {
                    toAccount.deposit(amount);
                    return true;
                }
                return false;
            case "Снятие":
                if (fromAccount != null && fromAccount.withdraw(amount)) {
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    // Геттеры для доступа к полям транзакции
    public Account getFromAccount() {
        return fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
