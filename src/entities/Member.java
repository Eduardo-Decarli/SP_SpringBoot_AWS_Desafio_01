package entities;

import java.util.Date;

public class Member extends Pessoa{
    private String address;
    private Integer phoneNumber;
    private String email;
    private Date dateAssociation;
    private Loan loan;

    public Member(String name, String address, Integer phoneNumber, String email, Date dateAssociation, Loan loan) {
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateAssociation() {
        return dateAssociation;
    }

    public Loan getLoan() {
        return loan;
    }
}
