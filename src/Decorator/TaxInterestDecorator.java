package Decorator;

import Strategy.Interface.Interest;

public class TaxInterestDecorator extends InterestDecorator {

    private final double taxRate;

    public TaxInterestDecorator(Interest innerInterest, double taxRate) {
        super(innerInterest);
        this.taxRate = taxRate;
    }

    @Override
    public float calculate(float balance) {
        float baseInterest = innerInterest.calculate(balance);
        float taxAmount = (float) (baseInterest * taxRate / 100);
        float netInterest = baseInterest - taxAmount;
        System.out.println("Applying tax: -" + taxAmount + " (" + taxRate + "%)");
        return netInterest;
    }
}
