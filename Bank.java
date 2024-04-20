package TheBankingSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import java.util.*;

public class Bank {
    private Map<String, Client> clients = new HashMap<>();
    private List<Transaction1> transactions = new ArrayList<>();

    public void registerClient(String username, Client client) {
        clients.put(username, client);
    }

    public Client login(String username, String password) {
        Client client = clients.get(username);
        if (client != null && client.checkPassword(password)) {
            return client;
        }
        return null;
    }

    public void logTransaction(Account fromAccount, Account toAccount, double amount, String transactionType) {
        Transaction1 transaction = new Transaction1(fromAccount, toAccount, amount, transactionType);
        transactions.add(transaction);
    }

    public List<Transaction1> getTransactions() {
        return Collections.unmodifiableList(transactions);
    }

    public Map<String, Client> getClients() {
        return Collections.unmodifiableMap(clients);
    }
}