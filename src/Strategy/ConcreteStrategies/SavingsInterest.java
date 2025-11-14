package Strategy.ConcreteStrategies;

import Strategy.Interface.Interest;

public class SavingsInterest implements Interest {
    private double savingInterestRate = 5; // 5%

    @Override
    public float calculate(float balance) {
        System.out.println("Calculating Savings Interest...");
        return (float) (savingInterestRate * balance / 100);
    }
}

