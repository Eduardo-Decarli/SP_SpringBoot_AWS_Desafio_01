package model.repositories.dao;

import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;

public interface LoanRepository {
    public void insertLoan(Loan loan);
    public Loan selectLoanByMember(Member member);
    public Loan selectLoanByStatus(StatusLoan status);
}
