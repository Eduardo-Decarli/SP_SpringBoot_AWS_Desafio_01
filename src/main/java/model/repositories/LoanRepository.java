package model.repositories;

import model.entities.Loan;
import model.entities.Member;

public interface LoanRepository {
    public void registerLoan(Member member, Loan loan);
    public void loanReturn();
}
