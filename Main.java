package TheBankingSystem;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager(bank);

        // Регистрация тестового пользователя
        Client testClient = new Client("Kate M", "Ginta Street", "pass123");
        bank.registerClient("kate_m", testClient);

        System.out.println("Добро пожаловать в банковскую систему!");
        while (true) {
            System.out.println("Введите 1 для входа, 0 для выхода:");
            int action = scanner.nextInt();
            scanner.nextLine();  // Очистка буфера после ввода числа

            if (action == 1) {
                System.out.println("Введите имя пользователя:");
                String username = scanner.nextLine();
                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                Client client = bank.login(username, password);
                if (client != null) {
                    System.out.println("Вход выполнен успешно для пользователя: " + client.getName());
                    boolean sessionActive = true;

                    while (sessionActive) {
                        System.out.println("Выберите действие: (1) Показать счета, (2) Создать новый счет, (3) Внести средства, (4) Снять средства, (5) Перевод между счетами, (6) Показать транзакции, (7) Выход");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Счета:");
                                for (Account account : client.getAccounts()) {
                                    System.out.println("Номер счета: " + account.getAccountNumber() + ", Баланс: " + account.getBalance());
                                }
                                break;
                            case 2:
                                System.out.println("Введите номер счета:");
                                String accountNumber = scanner.nextLine();
                                System.out.println("Введите начальный баланс:");
                                double initialBalance = scanner.nextDouble();
                                scanner.nextLine();
                                System.out.println("Введите тип счета (Current или Savings):");
                                String accountType = scanner.nextLine();
                                System.out.println("Введите параметр (лимит овердрафта или процентная ставка):");
                                double parameter = scanner.nextDouble();
                                scanner.nextLine();
                                accountManager.registerClientAndCreateAccount(username, client.getName(), client.getAddress(), password, accountNumber, initialBalance, accountType, parameter);
                                System.out.println("Счет создан.");
                                break;
                            case 3: // Внести средства
                                System.out.println("Введите номер счета:");
                                String depositAccountNumber = scanner.nextLine();
                                System.out.println("Введите сумму для внесения:");
                                double depositAmount = scanner.nextDouble();
                                scanner.nextLine();
                                Account depositAccount = client.getAccount(depositAccountNumber);
                                if (depositAccount != null) {
                                    depositAccount.deposit(depositAmount);
                                    bank.logTransaction(null, depositAccount, depositAmount, "DEPOSIT");
                                    System.out.println("Средства успешно внесены. Новый баланс: " + depositAccount.getBalance());
                                } else {
                                    System.out.println("Счет не найден.");
                                }
                                break;
                            case 4: // Снять средства
                                System.out.println("Введите номер счета:");
                                String withdrawalAccountNumber = scanner.nextLine();
                                System.out.println("Введите сумму для снятия:");
                                double withdrawalAmount = scanner.nextDouble();
                                scanner.nextLine();
                                Account withdrawalAccount = client.getAccount(withdrawalAccountNumber);
                                if (withdrawalAccount != null && withdrawalAccount.withdraw(withdrawalAmount)) {
                                    bank.logTransaction(withdrawalAccount, null, withdrawalAmount, "WITHDRAWAL");
                                    System.out.println("Средства успешно сняты. Новый баланс: " + withdrawalAccount.getBalance());
                                } else {
                                    System.out.println("Недостаточно средств или счет не найден.");
                                }
                                break;
                            case 5: // Перевод между счетами
                                System.out.println("Введите номер счета отправителя:");
                                String fromAccountNumber = scanner.nextLine();
                                System.out.println("Введите номер счета получателя:");
                                String toAccountNumber = scanner.nextLine();
                                System.out.println("Введите сумму перевода:");
                                double transferAmount = scanner.nextDouble();
                                scanner.nextLine();
                                Account fromAccount = client.getAccount(fromAccountNumber);
                                Account toAccount = client.getAccount(toAccountNumber);
                                if (fromAccount != null && toAccount != null && fromAccount.withdraw(transferAmount)) {
                                    toAccount.deposit(transferAmount);
                                    bank.logTransaction(fromAccount, toAccount, transferAmount, "TRANSFER");
                                    System.out.println("Перевод выполнен успешно. Новый баланс отправителя: " + fromAccount.getBalance() + ", Новый баланс получателя: " + toAccount.getBalance());
                                } else {
                                    System.out.println("Перевод не выполнен. Проверьте балансы и номера счетов.");
                                }
                                break;
                            case 6: // Показать транзакции
                                List<Transaction1> transactions = bank.getTransactions();
                                for (Transaction1 t : transactions) {
                                    System.out.println("Дата: " + t.getDateTime() + ", Тип: " + t.getTransactionType() + ", Сумма: " + t.getAmount());
                                }
                                break;
                            case 7:
                                sessionActive = false;
                                break;
                        }
                    }
                } else {
                    System.out.println("Ошибка входа. Попробуйте снова.");
                }
            } else if (action == 0) {
                System.out.println("Выход из системы.");
                break;
            }
        }
        scanner.close();
    }
}
