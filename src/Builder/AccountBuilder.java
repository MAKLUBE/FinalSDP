package Builder;

public class AccountBuilder {

    final int accountNumber;
    final String ownerName;

    float balance = 0f;
    String currency = "KZT";
    boolean debitCard = false;
    boolean creditCard = false;
    boolean smsNotifications = false;
    double cashbackPercent = 0.0;
    double dailyLimit = 0.0;

    public AccountBuilder(int accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    public AccountBuilder withBalance(float balance) {
        this.balance = balance;
        return this;
    }

    public AccountBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public AccountBuilder withDebitCard() {
        this.debitCard = true;
        return this;
    }

    public AccountBuilder withCreditCard() {
        this.creditCard = true;
        return this;
    }

    public AccountBuilder withSmsNotifications() {
        this.smsNotifications = true;
        return this;
    }

    public AccountBuilder withCashback(double cashbackPercent) {
        this.cashbackPercent = cashbackPercent;
        return this;
    }

    public AccountBuilder withDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
        return this;
    }

    public Account build() {
        return new Account(this);
    }
}
