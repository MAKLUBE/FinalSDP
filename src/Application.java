import Services.AccountService;
import AccService.BankingService;
import Observer.Context.AccountSubject;
import Observer.ConcreteObserver.NotificationObserver;
import Observer.ConcreteObserver.FraudDetectionObserver;

import java.util.Scanner;

public class Application {

    private static final AccountSubject subject = new AccountSubject();
    private static final FraudDetectionObserver fraudObserver = new FraudDetectionObserver();
    private static final AccountService accountService = new AccountService();
    private static final BankingService bankingService = new BankingService(accountService, subject, fraudObserver);

    public static void main(String[] args) {
        subject.addObserver(new NotificationObserver());
        subject.addObserver(fraudObserver);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Banking System ===");
            System.out.println("1. Create account (Builder)");
            System.out.println("2. Calculate interest for account (Strategy)");
            System.out.println("3. List accounts");
            System.out.println("4. Create loan agreement (Builder)");
            System.out.println("5. List loan agreements");
            System.out.println("6. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> bankingService.createAccount(sc);
                case 2 -> bankingService.calculateInterestForAccount(sc);
                case 3 -> bankingService.listAccounts();
                case 4 -> bankingService.createLoanAgreement(sc);
                case 5 -> bankingService.listLoanAgreements();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
