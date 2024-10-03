package model.services;

import exceptions.ServicesException;
import model.entities.Book;
import model.entities.Loan;
import model.entities.enumeration.StatusLoan;
import model.repositories.LoanRepository;
import model.services.dao.BookDAO;
import model.services.dao.LoanDAO;

public class LoanServices implements LoanRepository {

    private LoanDAO loanDao;
    private BookDAO bookDao = new BookDAO();

    public LoanServices(LoanDAO loanDao){
        this.loanDao = loanDao;
    }

    @Override
    public void registerLoan(Loan loan) {
        if(loan.getTaxFine() < 0){
            throw new ServicesException("Write a valid tax");
        }
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

        bookDao.updateBookQt(loan.getBook().getId(), 1);
        loanDao.insertLoan(loan);
    }

    @Override
    public Loan consultLoanByStatus(StatusLoan statusLoan) {
        return null;
    }

    @Override
    public void loanReturn() {

    }
}
