package model.services;

import exceptions.ServicesException;
import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatesLoan;
import model.repositories.LoanRepository;

public class LoanServices implements LoanRepository {

    @Override
    public void registerLoan(Member member, Loan loan) {
        if(member.getLoan() == null || member.getLoan().getStateLoan() == StatesLoan.CONCLUIDO){
            member.setLoan(loan);
            /* Lógica para cadastrar no banco de dados */
        }else{
            throw new ServicesException("Error when registering, there is a loan in progress");
        }
    }

    @Override
    public void loanReturn() {

    }
}
