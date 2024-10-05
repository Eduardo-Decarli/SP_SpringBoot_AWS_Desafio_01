package model.services;

import exceptions.ServicesException;
import model.entities.Book;
import model.entities.Loan;
import model.entities.enumeration.StatusLoan;
import model.repositories.LoanRepository;
import model.repositories.ReportRepository;
import model.services.dao.BookDAO;
import model.services.dao.LoanDAO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class LoanServices implements LoanRepository, ReportRepository<Loan> {

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
        if(loan.getBook().getQuantity() <= 0){
            throw new ServicesException("This book is not in stock");
        }

        long days = ChronoUnit.DAYS.between(loan.getDateLoan(), loan.getReturnDate());
        if(days > 5){
            loan.setStateLoan(StatusLoan.LATE);
                BigDecimal valueTax = new BigDecimal(0);
                BigDecimal taxPerDay = new BigDecimal(2.0);
                BigDecimal ratePerDay = new BigDecimal(0.05);

                for(int i = 5; i <= days; i++){
                    valueTax = valueTax.add(taxPerDay);
                    valueTax = valueTax.add(valueTax.multiply(ratePerDay));
                }

                valueTax = valueTax.setScale(3, RoundingMode.HALF_UP);
                loan.setTaxFine(valueTax);
        }

        bookDao.updateBookMinusQT(loan.getBook().getId(), 1);
        loanDao.insertLoan(loan);
    }

    @Override
    public List<Loan> findLoanByStatus(StatusLoan statusLoan) {
        List<Loan> listLoan = loanDao.selectLoanByStatus(statusLoan);
        if(listLoan.isEmpty()){
            System.out.println("There are not a loan with status: " + statusLoan);
            return null;
        }
        return listLoan;
    }

    @Override
    public void loanReturn(int id) {
        if(id <= 0){
            throw new ServicesException("Invalid id");
        }

        Loan loan = loanDao.selectLoanById(id);
        if(loan.getStateLoan().equals(StatusLoan.COMPLETE)){
            throw new ServicesException("This loan is already marked as COMPLETE");
        }
        
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

    @Override
    public String generateReport() {
        List<Loan> listLoans = loanDao.selectAllLoan();
        BigDecimal valueLoan = new BigDecimal(0);

        int activeLoan = 0;
        int lateLoan = 0;
        int completeLoan = 0;

        for(Loan correntLoan : listLoans){
            if(correntLoan.getStateLoan().equals(StatusLoan.COMPLETE)){
                completeLoan++;
            }else if(correntLoan.getStateLoan().equals(StatusLoan.LATE)){
                lateLoan++;
            } else if(correntLoan.getStateLoan().equals(StatusLoan.ACTIVE)){
                activeLoan++;
            }
            if(correntLoan.getStateLoan().equals(StatusLoan.COMPLETE)){
                valueLoan = valueLoan.add(correntLoan.getTaxFine());
            }
        }

        valueLoan = valueLoan.setScale(3, RoundingMode.HALF_UP);

        return  "=== Loan value report ===\n" +
                listLoans.size() +
                " Loans were registered." +
                "\nLoans with active status: " + activeLoan +
                "\nLoans with complete status: " + completeLoan +
                "\nLoans with late status: " + lateLoan +
                "\nThe total amount of fees received by clients were: " + valueLoan;
    }
}
