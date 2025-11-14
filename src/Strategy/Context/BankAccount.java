package Strategy.Context;

import Strategy.Interface.Interest;

public class BankAccount {
    private Interest interest;
    private float balance;
    private int accountNumber;
    private String name;

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float calculateInterest() {
        return interest.calculate(balance);
    }
}
