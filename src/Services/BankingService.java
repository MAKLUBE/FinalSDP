package Services;

import Builder.Loan.LoanAgreement;
import Builder.Loan.LoanAgreementBuilder;
import Observer.Context.AccountSubject;
import Observer.ConcreteObserver.FraudDetectionObserver;
import Services.AccountService;
import Strategy.ConcreteStrategies.LoanInterest;
import Strategy.ConcreteStrategies.SavingsInterest;
import Strategy.Context.BankAccount;
import Strategy.Interface.Interest;
import Builder.BankAccountBuilder;

import java.util.Scanner;

public class BankingService {

    private final AccountService accountService;
    private final AccountSubject subject;
    private final FraudDetectionObserver fraudObserver;

    public BankingService(AccountService accountService,
                          AccountSubject subject,
                          FraudDetectionObserver fraudObserver) {
        this.accountService = accountService;
        this.subject = subject;
        this.fraudObserver = fraudObserver;
    }

    public void createAccount(Scanner sc) {
        System.out.print("Enter account number: ");
        int number = sc.nextInt();
        sc.nextLine();

        if (accountService.accountNumberExists(number)) {
            System.out.println("Account with this number already exists!");
            return;
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

        BankAccountBuilder builder = new BankAccountBuilder(number, name)
                .withBalance(balance)
                .withCurrency("KZT")
                .withInterest(interest);

        BankAccount account = builder.build();
        accountService.addAccount(account);

        System.out.println("Account created.");
        subject.notifyObservers(account, "ACCOUNT_CREATED");
    }

    public void calculateInterestForAccount(Scanner sc) {
        if (!accountService.hasAccounts()) {
            System.out.println("No accounts yet. Create one first.");
            return;
        }

        System.out.print("Enter account number: ");
        int number = sc.nextInt();

        BankAccount account = accountService.findAccountByNumber(number);
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

    public void listAccounts() {
        if (!accountService.hasAccounts()) {
            System.out.println("No accounts.");
            return;
        }
        for (BankAccount acc : accountService.getAccounts()) {
            System.out.println(acc);
        }
    }

    public void createLoanAgreement(Scanner sc) {
        if (!accountService.hasAccounts()) {
            System.out.println("No accounts. Create account first.");
            return;
        }

        System.out.print("Enter existing account number: ");
        int number = sc.nextInt();
        sc.nextLine();

        BankAccount account = accountService.findAccountByNumber(number);
        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        if (!(account.getInterest() instanceof LoanInterest)) {
            System.out.println("This account is not a loan account. You can create a loan agreement only for loan accounts.");
            return;
        }

        System.out.print("Enter agreement id: ");
        int agreementId = sc.nextInt();

        float principal;
        while (true) {
            System.out.print("Enter loan principal: ");
            if (sc.hasNextFloat()) {
                principal = sc.nextFloat();
                sc.nextLine();
                if (principal > 0) {
                    break;
                }
                System.out.println("Principal must be positive.");
            } else {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }

        float annualRate;
        while (true) {
            System.out.print("Enter annual rate (%): ");
            if (sc.hasNextFloat()) {
                annualRate = sc.nextFloat();
                sc.nextLine();
                if (annualRate > 0) {
                    break;
                }
                System.out.println("Rate must be positive.");
            } else {
                System.out.println("Please enter a valid number.");
                sc.nextLine();
            }
        }

        System.out.print("Enter term in months: ");
        int termMonths = sc.nextInt();
        sc.nextLine();

        Interest interestStrategy = new LoanInterest();

        LoanAgreementBuilder builder = new LoanAgreementBuilder(agreementId, account)
                .withPrincipal(principal)
                .withAnnualRate(annualRate)
                .withTermMonths(termMonths)
                .withInterestStrategy(interestStrategy);

        LoanAgreement loan = builder.build();
        accountService.addLoanAgreement(loan);

        System.out.println("Loan agreement created:");
        System.out.println(loan);
    }

    public void listLoanAgreements() {
        if (!accountService.hasLoans()) {
            System.out.println("No loan agreements.");
            return;
        }
        for (LoanAgreement loan : accountService.getLoanAgreements()) {
            System.out.println(loan);
        }
    }
}
