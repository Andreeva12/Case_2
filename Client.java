package TheBankingSystem;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

public class Client {
    private String name;
    private String address;
    private String password;
    private List<Account> accounts;

    public Client(String name, String address, String password) {
        this.name = name;
        this.address = address;
        this.password = password;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public boolean checkPassword(String password) {
        return password != null && this.password.equals(password);
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }
}