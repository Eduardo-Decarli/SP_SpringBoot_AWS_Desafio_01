package model.repositories;

import model.entities.Loan;
import model.entities.enumeration.StatusLoan;

public interface LoanRepository {
    public void registerLoan(Loan loan);
    public Loan consultLoanByStatus(StatusLoan statusLoan);
    public void loanReturn();
}
