package model.entities;

import model.entities.enumeration.StatesLoan;
import model.services.LoanService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Loan implements LoanService {

    private List<Book> books;

    private Member member;
    private LocalDate dateLoan;
    private LocalDate returnDate;
    private StatesLoan stateLoan;
    private Double taxFine;

    public Loan(List<Book> books, Member member, LocalDate dateLoan, LocalDate returnDate, StatesLoan stateLoan, Double taxFine) {
        this.books = books;
        this.member = member;
        this.dateLoan = dateLoan;
        this.returnDate = returnDate;
        this.stateLoan = stateLoan;
        this.taxFine = taxFine;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LocalDate getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(LocalDate dateLoan) {
        this.dateLoan = dateLoan;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public StatesLoan getStateLoan() {
        return stateLoan;
    }

    public void setStateLoan(StatesLoan stateLoan) {
        this.stateLoan = stateLoan;
    }

    public Double getTaxFine() {
        return taxFine;
    }

    @Override
    public void registerLoan() {
        if(member.getLoan() == null || member.getLoan().getStateLoan() == StatesLoan.CONCLUIDO){
            member.setLoan(this);
            /* Lógica para cadastrar no banco de dados */
        }else{
            throw new EntiException("Error when registering, there is a loan in progress");
        }
    }

    @Override
    public void loanReturn() {

    }

    @Override
    public String toString() {
        return "Loan{" +
                "books=" + books +
                ", member=" + member.getName() +
                ", dateLoan=" + dateLoan +
                ", returnDate=" + returnDate +
                ", stateLoan=" + stateLoan +
                ", taxFine=" + taxFine +
                '}';
    }
}
