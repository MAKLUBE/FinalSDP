package Builder.Loan;
import Strategy.Context.BankAccount;
import Strategy.Interface.Interest;

public class LoanAgreement {

    private final int agreementId;
    private final BankAccount account;
    private final float principal;
    private final float annualRate;
    private final int termMonths;
    private final float monthlyPayment;
    private final Interest interest;

    public LoanAgreement(int agreementId,
                         BankAccount account,
                         float principal,
                         float annualRate,
                         int termMonths,
                         float monthlyPayment,
                         Interest interest) {
        this.agreementId = agreementId;
        this.account = account;
        this.principal = principal;
        this.annualRate = annualRate;
        this.termMonths = termMonths;
        this.monthlyPayment = monthlyPayment;
        this.interest = interest;
    }

    public int getAgreementId() {
        return agreementId;
    }

    public BankAccount getAccount() {
        return account;
    }

    public float getPrincipal() {
        return principal;
    }

    public float getAnnualRate() {
        return annualRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public float getMonthlyPayment() {
        return monthlyPayment;
    }

    public Interest getInterest() {
        return interest;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "agreementId=" + agreementId +
                ", accountNumber=" + account.getAccountNumber() +
                ", ownerName='" + account.getOwnerName() + '\'' +
                ", principal=" + principal +
                ", annualRate=" + annualRate +
                ", termMonths=" + termMonths +
                ", monthlyPayment=" + monthlyPayment +
                ", interest=" + (interest != null ? interest.getClass().getSimpleName() : "null") +
                '}';
    }
}
