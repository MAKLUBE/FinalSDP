package Builder;

import Strategy.Context.BankAccount;
import Strategy.Interface.Interest;

public class BankAccountBuilder {

    private final int accountNumber;
    private final String ownerName;
    private float balance = 0f;
    private String currency = "KZT";
    private boolean debitCard = false;
    private boolean creditCard = false;
    private boolean smsNotifications = false;
    private double cashbackPercent = 0.0;
    private double dailyLimit = 0.0;
    private Interest interest;

    public BankAccountBuilder(int accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    public BankAccountBuilder withBalance(float balance) {
        this.balance = balance;
        return this;
    }

    public BankAccountBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public BankAccountBuilder withDebitCard() {
        this.debitCard = true;
        return this;
    }

    public BankAccountBuilder withCreditCard() {
        this.creditCard = true;
        return this;
    }

    public BankAccountBuilder withSmsNotifications() {
        this.smsNotifications = true;
        return this;
    }

    public BankAccountBuilder withCashback(double cashbackPercent) {
        this.cashbackPercent = cashbackPercent;
        return this;
    }

    public BankAccountBuilder withDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
        return this;
    }

    public BankAccountBuilder withInterest(Interest interest) {
        this.interest = interest;
        return this;
    }

    public BankAccount build() {
        return new BankAccount(
                accountNumber,
                ownerName,
                balance,
                currency,
                debitCard,
                creditCard,
                smsNotifications,
                cashbackPercent,
                dailyLimit,
                interest
        );
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "accountNumber=" + accountNumber +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", debitCard=" + debitCard +
                ", creditCard=" + creditCard +
                ", smsNotifications=" + smsNotifications +
                ", cashbackPercent=" + cashbackPercent +
                ", dailyLimit=" + dailyLimit +
                ", interest=" + (interest != null ? interest.getClass().getSimpleName() : "null") +
                '}';
    }
}
