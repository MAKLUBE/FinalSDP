package Observer.Interface;

import Strategy.Context.BankAccount;

public interface AccountObserver {
    void update(BankAccount account, String event);
}
