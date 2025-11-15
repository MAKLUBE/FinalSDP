package Observer.ConcreteObserver;

import Observer.Interface.AccountObserver;
import Strategy.Context.BankAccount;

public class FraudDetectionObserver implements AccountObserver {

    private final float largeBalanceThreshold = 1_000_000f;

    @Override
    public void update(BankAccount account, String event) {
        if ("ACCOUNT_CREATED".equals(event) && account.getBalance() > largeBalanceThreshold) {
            System.out.println("[FRAUD ALERT] Large initial balance. " +
                    "accountNumber=" + account.getAccountNumber() +
                    ", owner='" + account.getOwnerName() + '\'' +
                    ", balance=" + account.getBalance());
        }

        if ("INTEREST_CALCULATED".equals(event) && account.getBalance() > largeBalanceThreshold) {
            System.out.println("[FRAUD ALERT] Interest calculated for high-balance account. " +
                    "accountNumber=" + account.getAccountNumber() +
                    ", owner='" + account.getOwnerName() + '\'');
        }
    }
}
