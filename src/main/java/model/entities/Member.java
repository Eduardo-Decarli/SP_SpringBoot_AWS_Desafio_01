package model.entities;

import model.services.dao.BookDAO;

import java.time.LocalDate;

public class Member extends People{
    private String address;
    private long phoneNumber;
    private String email;
    private LocalDate dateAssociation;

    private Loan loan;

    public Member(String name, String address, long phoneNumber, String email, LocalDate dateAssociation) {
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
    public String toString() {
        return "Name: " + getName() +
                ", Address: " + address +
                ", Phone Number: " + phoneNumber +
                ", Email='" + email +
                ", Date of Association: " + dateAssociation +
                ", Loan: " + (loan != null ? loan.toString() : "does not have a loan");
    }
}
