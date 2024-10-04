package model.services;

import exceptions.ServicesException;
import model.entities.Book;
import model.entities.Loan;
import model.entities.enumeration.StatusLoan;
import model.repositories.LoanRepository;
import model.services.dao.BookDAO;
import model.services.dao.LoanDAO;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LoanServices implements LoanRepository {

    private LoanDAO loanDao;
    private BookDAO bookDao = new BookDAO();

    public LoanServices(LoanDAO loanDao){
        this.loanDao = loanDao;
    }

    @Override
    public void registerLoan(Loan loan) {
        if(loan.getDateLoan().isAfter(loan.getReturnDate())){
            throw new ServicesException("The date of loan is after return date!");
        }
        if(loan.getReturnDate().isBefore(loan.getDateLoan())){
            throw new ServicesException("The return date is before date of loan!");
        }
        Book bookConsult = bookDao.selectBookByIsbn(loan.getBook().getIsbn());
        if(bookConsult.getQuantity() < 0){
            throw new ServicesException("This book is not in stock");
        }

        long days = ChronoUnit.DAYS.between(loan.getDateLoan(), loan.getReturnDate());
        if(days > 5){
            loan.setStateLoan(StatusLoan.LATE);
        }

        bookDao.updateBookMinusQT(loan.getBook().getId(), 1);
        loanDao.insertLoan(loan);
    }

    @Override
    public List<Loan> findLoanByStatus(StatusLoan statusLoan) {
        List<Loan> listLoan = loanDao.selectLoanByStatus(statusLoan);
        List<Loan> listTaxFine = new ArrayList<>();
        if(listLoan.isEmpty()){
            System.out.println("There are not a loan with status: " + statusLoan);
            return null;
        }
        if(listLoan.getFirst().getStateLoan().equals(StatusLoan.LATE)){
            for(Loan correntLoan : listLoan){
                BigDecimal valueTax = new BigDecimal(0);
                BigDecimal taxPerDay = new BigDecimal(0.2);
                BigDecimal ratePerDay = new BigDecimal(0.05);

                long days = ChronoUnit.DAYS.between(correntLoan.getDateLoan(), correntLoan.getReturnDate());

                for(int i = 0; i <= days; i++){
                    valueTax = valueTax.add(taxPerDay);
                    valueTax = valueTax.add(valueTax.multiply(ratePerDay));
                }
                correntLoan.setTaxFine(valueTax);
                listTaxFine.add(correntLoan);
            }
            return listTaxFine;
        }
        return listLoan;
    }

    @Override
    public void loanReturn(int id) {
        if(id <= 0){
            throw new ServicesException("Invalid id");
        }
        Loan loan = loanDao.selectLoanById(id);
        bookDao.updateBookPlusQT(loan.getBook().getId(), 1);
        loanDao.updateLoanCOMPLETE(id);
    }

    @Override
    public Loan findLoanById(int id) {
        if(id <= 0){
            throw new ServicesException("Invalid id");
        }
        return loanDao.selectLoanById(id);
    }
}
