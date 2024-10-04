package model.repositories.dao;

import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;

import java.util.List;

public interface LoanRepositoryDAO {
    public void insertLoan(Loan loan);
    public Loan selectLoanByMember(Member member);
    public List<Loan> selectLoanByStatus(StatusLoan status);
    public List<Loan> selectAllLoan();
    public Loan selectLoanById(int id);
    public void updateLoanCOMPLETE(int id);
}
