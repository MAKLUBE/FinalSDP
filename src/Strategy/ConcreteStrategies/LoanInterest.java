package Strategy.ConcreteStrategies;

import Strategy.Interface.Interest;

public class LoanInterest implements Interest {
    private double loanInterestRate = 10; // 10%

    @Override
    public float calculate(float balance) {
        System.out.println("Calculating loan interest...");
        return (float) (loanInterestRate * balance / 100);
    }
}
