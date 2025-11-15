package Builder;

public class Account {
    private final int accountNumber;
    private final String ownerName;

    private final float balance;
    private final String currency;
    private final boolean debitCard;
    private final boolean creditCard;
    private final boolean smsNotifications;
    private final double cashbackPercent;
    private final double dailyLimit;

    Account(AccountBuilder builder) {
        this.accountNumber = builder.accountNumber;
        this.ownerName = builder.ownerName;
        this.balance = builder.balance;
        this.currency = builder.currency;
        this.debitCard = builder.debitCard;
        this.creditCard = builder.creditCard;
        this.smsNotifications = builder.smsNotifications;
        this.cashbackPercent = builder.cashbackPercent;
        this.dailyLimit = builder.dailyLimit;
    }

    // геттеры (на защите можно показать, но не обязательно все использовать)
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

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", debitCard=" + debitCard +
                ", creditCard=" + creditCard +
                ", smsNotifications=" + smsNotifications +
                ", cashbackPercent=" + cashbackPercent +
                ", dailyLimit=" + dailyLimit +
                '}';
    }
}
