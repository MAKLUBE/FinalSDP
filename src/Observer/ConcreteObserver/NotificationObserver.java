package Observer.ConcreteObserver;

import Observer.Interface.AccountObserver;
import Strategy.Context.BankAccount;

public class NotificationObserver implements AccountObserver {

    @Override
    public void update(BankAccount account, String event) {
        System.out.println("[Notification] Event: " + event +
                ", accountNumber=" + account.getAccountNumber() +
                ", owner='" + account.getOwnerName() + "'");
    }
}