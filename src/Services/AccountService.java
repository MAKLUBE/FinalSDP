package Services;

import Builder.Loan.LoanAgreement;
import Strategy.Context.BankAccount;
import java.util.ArrayList;
import java.util.List;
public class AccountService {

    private final List<BankAccount> accounts = new ArrayList<>();
    private final List<LoanAgreement> loans = new ArrayList<>();

    public void addAccount(BankAccount account) {
        accounts.add(account);
    }

    public BankAccount findAccountByNumber(int number) {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber() == number) {
                return acc;
            }
        }
        return null;
    }

    public boolean accountNumberExists(int number) {
        return findAccountByNumber(number) != null;
    }

    public List<BankAccount> getAccounts() {
        return accounts;
    }

    public boolean hasAccounts() {
        return !accounts.isEmpty();
    }

    public void addLoanAgreement(LoanAgreement loanAgreement) {
        loans.add(loanAgreement);
    }

    public List<LoanAgreement> getLoanAgreements() {
        return loans;
    }

    public boolean hasLoans() {
        return !loans.isEmpty();
    }
}
