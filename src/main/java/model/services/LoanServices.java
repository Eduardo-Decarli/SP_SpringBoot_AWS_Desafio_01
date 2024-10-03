package model.services;

import exceptions.ServicesException;
import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;
import model.repositories.LoanRepository;
import model.services.dao.LoanDAO;

public class LoanServices implements LoanRepository {

    private LoanDAO loanDao;

    public LoanServices(LoanDAO loanDao){
        this.loanDao = loanDao;
    }

    @Override
    public void registerLoan(Loan loan) {
        loanDao.insertLoan(loan);
    }

    @Override
    public Loan consultLoanByStatus(StatusLoan statusLoan) {
        return null;
    }

    @Override
    public void loanReturn() {

    }

    /*
        @Override
        public void registerLoan(Member member, Loan loan) {
            if(member.getLoan() == null || member.getLoan().getStateLoan() == StatusLoan.CONCLUIDO){
                member.setLoan(loan);
                /* Lógica para cadastrar no banco de dados *//*
        }else{
            throw new ServicesException("Error when registering, there is a loan in progress");
        }
    }
*/
}
