package entities;

import entities.enumeration.StatesLoan;

import java.util.Date;
import java.util.List;

public class Loan {

    private List<Book> books;

    private Member member;
    private Date dateLoan;
    private Date returnDate;
    private StatesLoan stateLoan;
    private Double taxFine;

    public Loan(List<Book> books, Member member, Date dateLoan, Date returnDate, StatesLoan stateLoan, Double taxFine) {
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

    public Date getDateLoan() {
        return dateLoan;
    }

    public void setDateLoan(Date dateLoan) {
        this.dateLoan = dateLoan;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
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
}
