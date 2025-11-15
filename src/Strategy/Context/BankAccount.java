package Strategy.Context;

import Strategy.Interface.Interest;

public class BankAccount {

    private final int accountNumber;
    private final String ownerName;
    private final float balance;
    private final String currency;
    private final boolean debitCard;
    private final boolean creditCard;
    private final boolean smsNotifications;
    private final double cashbackPercent;
    private final double dailyLimit;
    private final Interest interest;

    public BankAccount(int accountNumber,
                       String ownerName,
                       float balance,
                       String currency,
                       boolean debitCard,
                       boolean creditCard,
                       boolean smsNotifications,
                       double cashbackPercent,
                       double dailyLimit,
                       Interest interest) {

        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
        this.currency = currency;
        this.debitCard = debitCard;
        this.creditCard = creditCard;
        this.smsNotifications = smsNotifications;
        this.cashbackPercent = cashbackPercent;
        this.dailyLimit = dailyLimit;
        this.interest = interest;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public float getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public boolean hasDebitCard() {
        return debitCard;
    }

    public boolean hasCreditCard() {
        return creditCard;
    }

    public boolean hasSmsNotifications() {
        return smsNotifications;
    }

    public double getCashbackPercent() {
        return cashbackPercent;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public Interest getInterest() {
        return interest;
    }

    public float calculateInterest() {
        if (interest == null) {
            return 0;
        }
        return interest.calculate(balance);
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
