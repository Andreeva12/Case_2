package TheBankingSystem;

public class SavingsAccount extends Account {
    private double interestRate;  // Процентная ставка

    public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
        super(accountNumber, initialBalance);
        this.interestRate = interestRate;
    }

    public void applyInterest() {
        balance += balance * interestRate / 100;
    }
}
