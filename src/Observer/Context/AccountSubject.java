package Observer.Context;
import Observer.Interface.AccountObserver;
import Strategy.Context.BankAccount;

import java.util.ArrayList;
import java.util.List;

public class AccountSubject {

    private final List<AccountObserver> observers = new ArrayList<>();

    public void addObserver(AccountObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(AccountObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(BankAccount account, String event) {
        for (AccountObserver observer : observers) {
            observer.update(account, event);
        }
    }
}
