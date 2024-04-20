package TheBankingSystem;

public class AccountManager {
    private Bank bank;
    public AccountManager(Bank bank) {
        this.bank = bank;
    }

    public void registerClientAndCreateAccount(String username, String name, String address, String password, String accountNumber, double initialBalance, String accountType, double parameter) throws IllegalArgumentException {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        Client client = bank.getClients().get(username);
        if (client == null) {
            client = new Client(name, address, password);
            bank.registerClient(username, client);
        }
        Account account;
        if ("Current".equals(accountType)) {
            account = new CurrentAccount(accountNumber, initialBalance, parameter);
        } else if ("Savings".equals(accountType)) {
            account = new SavingsAccount(accountNumber, initialBalance, parameter);
        } else {
            throw new IllegalArgumentException("Invalid account type: " + accountType);
        }
        client.addAccount(account);
    }
}
