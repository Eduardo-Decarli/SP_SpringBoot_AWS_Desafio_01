package model.repositories;

import model.entities.Loan;
import model.entities.enumeration.StatusLoan;

import java.util.List;

public interface LoanRepository {
    public void registerLoan(Loan loan);
    public List<Loan> findLoanByStatus(StatusLoan statusLoan);
    public void loanReturn(int id);
    public Loan findLoanById(int id);
}
