import Builder.BankAccountBuilder;
import Observer.Context.AccountSubject;
import Observer.ConcreteObserver.NotificationObserver;
import Observer.ConcreteObserver.FraudDetectionObserver;
import Strategy.ConcreteStrategies.LoanInterest;
import Strategy.ConcreteStrategies.SavingsInterest;
import Strategy.Context.BankAccount;
import Strategy.Interface.Interest;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Application {

    private static final List<BankAccount> accounts = new ArrayList<>();
    private static final AccountSubject subject = new AccountSubject();

    public static void main(String[] args) {
        subject.addObserver(new NotificationObserver());
        subject.addObserver(new FraudDetectionObserver());

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create account");
            System.out.println("2. Calculate interest for account ");
            System.out.println("3. List accounts");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount(sc);
                    break;
                case 2:
                    calculateInterestForAccount(sc);
                    break;
                case 3:
                    listAccounts();
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void createAccount(Scanner sc) {
        int id;
        while (true) {
            System.out.print("Enter account number: ");
            if (sc.hasNextInt()) {
                id = sc.nextInt();
                sc.nextLine();

                if (findAccountByNumber(id) != null) {
                    System.out.println("Account with this number already exists!");
                    return;
                }

                if (id < 0) {
                    System.out.println("Account number cannot be negative. Try again.");
                    continue;
                }

                break;
            } else {
                System.out.println("Please enter a valid account number.");
                sc.nextLine();
            }
        }


        String name;
        while (true) {
            System.out.print("Enter owner name: ");
            name = sc.nextLine().trim();

            if (name.matches("[A-Za-z]+")) {
                break;
            }

            System.out.println("Name must contain only letters (A–Z, a–z). Try again.");
        }

        float balance;

        while (true) {
            System.out.print("Initial balance: ");

            if (sc.hasNextFloat()) {
                balance = sc.nextFloat();
                sc.nextLine();

                if (balance < 0) {
                    System.out.println("Balance cannot be negative. Try again.");
                    continue;
                }

                break;
            } else {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }


        System.out.println("Choose account type:");
        System.out.println("1. Savings (5%)");
        System.out.println("2. Loan (10%)");
        int type = sc.nextInt();
        sc.nextLine();

        Interest interest;
        if (type == 1) {
            interest = new SavingsInterest();
        } else if (type == 2) {
            interest = new LoanInterest();
        } else {
            System.out.println("Invalid type");
            return;
        }

        // Джиги здесь билдер
        BankAccountBuilder builder = new BankAccountBuilder(id, name)
                .withBalance(balance)
                .withCurrency("KZT")
                .withInterest(interest);

        BankAccount account = builder.build();
        accounts.add(account);

        System.out.println("Account created.");
        subject.notifyObservers(account, "ACCOUNT_CREATED");
    }

    private static void calculateInterestForAccount(Scanner sc) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts yet. Create one first.");
            return;
        }

        System.out.print("Enter account number: ");
        int number = sc.nextInt();

        BankAccount account = findAccountByNumber(number);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        float interest = account.calculateInterest();
        System.out.println("Account: " + account.getAccountNumber());
        System.out.println("Balance: " + account.getBalance());
        System.out.println("Interest: " + interest);
        System.out.println("Total: " + (account.getBalance() + interest));

        subject.notifyObservers(account, "INTEREST_CALCULATED");
    }

    private static void listAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts.");
            return;
        }
        for (BankAccount acc : accounts) {
            System.out.println(acc);
        }
    }

    private static BankAccount findAccountByNumber(int number) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == number) {
                return acc;
            }
        }
        return null;
    }
}
