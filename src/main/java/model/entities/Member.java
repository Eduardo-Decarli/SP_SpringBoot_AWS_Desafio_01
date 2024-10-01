package model.entities;

import dao.BookDAO;
import model.services.BookServices;

import java.time.LocalDate;

public class Member extends People implements BookServices {
    private String address;
    private long phoneNumber;
    private String email;
    private LocalDate dateAssociation;

    private Loan loan;

    public Member(String name, String address, long phoneNumber, String email, LocalDate dateAssociation, Loan loan) {
        super(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateAssociation = dateAssociation;
        this.loan = loan;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateAssociation() {
        return dateAssociation;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan){
        this.loan = loan;
    }

    @Override
    public void registerBook(Book book) {
        BookDAO bsDao = new BookDAO();
        bsDao.insertBook(book);
    }

    @Override
    public Book sourchBook(Book book) {
        return null;
    }


    @Override
    public String toString() {
        return "Member{" +
                "name= " + getName() +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", dateAssociation=" + dateAssociation +
                ", loan=" + (loan != null ? loan.toString() : "does not have a loan") +
                '}';
    }
}
