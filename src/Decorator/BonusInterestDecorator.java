package Decorator;

import Strategy.Interface.Interest;

public class BonusInterestDecorator extends InterestDecorator {

    private final double bonusRate;

    public BonusInterestDecorator(Interest innerInterest, double bonusRate) {
        super(innerInterest);
        this.bonusRate = bonusRate;
    }

    @Override
    public float calculate(float balance) {
        float baseInterest = innerInterest.calculate(balance);
        float bonusAmount = (float) (baseInterest * bonusRate / 100);
        float totalInterest = baseInterest + bonusAmount;
        System.out.println("Applying bonus: +" + bonusAmount + " (" + bonusRate + "%)");
        return totalInterest;
    }
}
