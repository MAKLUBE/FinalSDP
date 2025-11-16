package Builder.Loan;
import Strategy.Context.BankAccount;
import Strategy.Interface.Interest;

public class LoanAgreementBuilder {

    private final int agreementId;
    private final BankAccount account;

    private float principal;
    private float annualRate;
    private int termMonths;
    private Interest interest;

    public LoanAgreementBuilder(int agreementId, BankAccount account) {
        this.agreementId = agreementId;
        this.account = account;
    }

    public LoanAgreementBuilder withPrincipal(float principal) {
        this.principal = principal;
        return this;
    }

    public LoanAgreementBuilder withAnnualRate(float annualRate) {
        this.annualRate = annualRate;
        return this;
    }

    public LoanAgreementBuilder withTermMonths(int termMonths) {
        this.termMonths = termMonths;
        return this;
    }

    public LoanAgreementBuilder withInterestStrategy(Interest interest) {
        this.interest = interest;
        return this;
    }

    public LoanAgreement build() {
        float monthlyPayment = calculateMonthlyPayment(principal, annualRate, termMonths);
        return new LoanAgreement(
                agreementId,
                account,
                principal,
                annualRate,
                termMonths,
                monthlyPayment,
                interest
        );
    }

    private float calculateMonthlyPayment(float principal, float annualRate, int termMonths) {
        if (termMonths <= 0 || annualRate <= 0 || principal <= 0) {
            return 0f;
        }
        double r = annualRate / 100.0 / 12.0;
        double payment = principal * r / (1 - Math.pow(1 + r, -termMonths));
        return (float) payment;
    }
}
