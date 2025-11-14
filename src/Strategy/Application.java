package Strategy;

import Strategy.ConcreteStrategies.LoanInterest;
import Strategy.ConcreteStrategies.SavingsInterest;
import Strategy.Context.BankAccount;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = new BankAccount();
        SavingsInterest savings = new SavingsInterest();
        LoanInterest loans = new LoanInterest();

        while (true) {
            try {
                System.out.println("Choose your account");
                System.out.println("1. Savings Account");
                System.out.println("2. Loan Account");
                System.out.println("3. Exit");
                int choice = sc.nextInt();

                if (choice == 1) {
                    account.setInterest(savings);
                } else if (choice == 2) {
                    account.setInterest(loans);
                }
                else if (choice == 3) {
                    System.out.println("Goodbye");
                    break;
                }
                else {
                    System.out.println("Invalid choice");
                    continue;
                }

                System.out.println("Enter your balance: ");
                float balance = sc.nextFloat();

                if (balance < 0){
                    System.out.println("Invalid balance! Try again");
                    continue;
                }
                account.setBalance(balance);

                double interest = account.calculateInterest();
                if (account.getInterest() instanceof SavingsInterest){
                    System.out.println("Your balance: " + account.getBalance());
                    System.out.println("Earned interest: +" + interest);
                    System.out.println("Total balance (with interest): " + (account.getBalance() + interest));
                }
                else if (account.getInterest() instanceof LoanInterest) {
                    System.out.println("Loan amount: " + account.getBalance());
                    System.out.println("Added interest : +" + interest);
                    System.out.println("Total amount to pay: " + (account.getBalance() + interest));
                }
                else {
                    System.out.println("Invalid choice");
                }

            }
            catch (Exception e) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }
}
