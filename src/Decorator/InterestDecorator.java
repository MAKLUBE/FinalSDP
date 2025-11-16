package Decorator;

import Strategy.Interface.Interest;

public abstract class InterestDecorator implements Interest {

    protected final Interest innerInterest;

    protected InterestDecorator(Interest innerInterest) {
        this.innerInterest = innerInterest;
    }
}
