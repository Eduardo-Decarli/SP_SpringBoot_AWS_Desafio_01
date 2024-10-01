package entities;

import entities.services.BookServices;

import java.time.LocalDate;

public class Member extends Pessoa implements BookServices {
    private String address;
    private Double phoneNumber;
    private String email;
    private LocalDate dateAssociation;

    private Loan loan;

    public Member(String name, String address, Double phoneNumber, String email, LocalDate dateAssociation) {
        super(name);
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.dateAssociation = dateAssociation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Double phoneNumber) {
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

    @Override
    public void registerBook(Book book) {

    }

    @Override
    public Book sourchBook(Book book) {
        return null;
    }

    @Override
    public void loanReturn() {

    }
}
