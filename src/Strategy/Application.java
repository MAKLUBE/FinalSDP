package Strategy;

import Builder.Account;
import Builder.AccountBuilder;
import Strategy.ConcreteStrategies.LoanInterest;
import Strategy.ConcreteStrategies.SavingsInterest;
import Strategy.Context.BankAccount;

import java.util.Scanner;

public class Application {

    private static void runStrategyDemo(Scanner sc) {
        BankAccount account = new BankAccount();
        SavingsInterest savings = new SavingsInterest();
        LoanInterest loans = new LoanInterest();

        while (true) {
            System.out.println("\n[Strategy] Choose your account:");
            System.out.println("1. Savings Account");
            System.out.println("2. Loan Account");
            System.out.println("3. Back to main menu");

            int choice = sc.nextInt();

            if (choice == 3) {
                break;
            }

            if (choice == 1) {
                account.setInterest(savings);
            } else if (choice == 2) {
                account.setInterest(loans);
            } else {
                System.out.println("Invalid choice");
                continue;
            }

            System.out.print("Enter your balance: ");
            float balance = sc.nextFloat();

            if (balance < 0) {
                System.out.println("Invalid balance! Try again");
                continue;
            }

            account.setBalance(balance);

            float interest = account.calculateInterest();

            if (account.getInterest() instanceof SavingsInterest) {
                System.out.println("Your balance: " + account.getBalance());
                System.out.println("Earned interest: +" + interest);
                System.out.println("Total balance (with interest): " +
                        (account.getBalance() + interest));
            } else if (account.getInterest() instanceof LoanInterest) {
                System.out.println("Loan amount: " + account.getBalance());
                System.out.println("Added interest : +" + interest);
                System.out.println("Total amount to pay: " +
                        (account.getBalance() + interest));
            }
        }
    }

    private static void runBuilderDemo(Scanner sc) {
        System.out.println("\n[Builder] Create new bank account");

        System.out.print("Enter account number: ");
        int number = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter owner name: ");
        String name = sc.nextLine();

        AccountBuilder builder = new AccountBuilder(number, name);

        System.out.print("Initial balance: ");
        float balance = sc.nextFloat();
        builder.withBalance(balance);
        sc.nextLine();

        System.out.print("Currency (press Enter for default KZT): ");
        String currency = sc.nextLine();
        if (!currency.isBlank()) {
            builder.withCurrency(currency);
        }

        System.out.print("Add debit card? (y/n): ");
        String ans = sc.nextLine();
        if (ans.equalsIgnoreCase("y")) {
            builder.withDebitCard();
        }

        System.out.print("Add credit card? (y/n): ");
        ans = sc.nextLine();
        if (ans.equalsIgnoreCase("y")) {
            builder.withCreditCard();
        }

        System.out.print("Enable SMS notifications? (yes/no): ");
        ans = sc.nextLine();
        if (ans.equalsIgnoreCase("yes")) {
            builder.withSmsNotifications();
        }

        Account account = builder.build();

        System.out.println("\nAccount created:");
        System.out.println(account);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Interest calculation (Strategy)");
            System.out.println("2. Create account (Builder)");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> runStrategyDemo(sc);
                case 2 -> runBuilderDemo(sc);
                case 3 -> {
                    System.out.println("Goodbye!");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
