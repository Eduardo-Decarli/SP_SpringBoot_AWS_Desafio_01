package model.entities;

import model.entities.enumeration.StatusLoan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Loan {

    private int id;
    private Book book;
    private Member member;
    private LocalDate dateLoan;
    private LocalDate returnDate;
    private StatusLoan stateLoan;
    private BigDecimal taxFine;

    public Loan(Book book, Member member, LocalDate dateLoan, LocalDate returnDate, StatusLoan stateLoan, BigDecimal taxFine) {
        this.book = book;
        this.member = member;
        this.dateLoan = dateLoan;
        this.returnDate = returnDate;
        this.stateLoan = stateLoan;
        this.taxFine = taxFine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBooks(Book book) {
        this.book = book;
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

    public StatusLoan getStateLoan() {
        return stateLoan;
    }

    public void setStateLoan(StatusLoan stateLoan) {
        this.stateLoan = stateLoan;
    }

    public BigDecimal getTaxFine() {
        return taxFine;
    }

    public void setTaxFine(BigDecimal taxFine) {
        this.taxFine = taxFine;
    }

    @Override
    public String toString() {
        return  "Book " + book +
                ", Member: " + member.getName() +
                ", Date of Loan: " + dateLoan +
                ", Date of Return: " + returnDate +
                ", Status of Loan: " + stateLoan +
                ", Tax: " + taxFine;
    }
}
