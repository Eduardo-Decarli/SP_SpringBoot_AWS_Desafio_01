package model.services.dao;

import exceptions.DaoException;
import model.entities.Book;
import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;
import model.repositories.dao.LoanRepository;
import model.services.BookServices;
import model.services.MemberServices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static model.services.dao.ConnectionFactory.getConnection;

public class LoanDAO implements LoanRepository {

    Connection conn = getConnection();

    @Override
    public void insertLoan(Loan loan) {
        PreparedStatement stmt = null;

        try{
            BookServices bookServices = new BookServices(new BookDAO());
            Book bookSelectByISBN = bookServices.findBookByIsbn(loan.getBook().getIsbn());

            MemberServices memberServices = new MemberServices(new MemberDAO());
            Member memberSelectById = memberServices.findMemberByEmail(loan.getMember().getEmail()); //Fazer a chave estrangeira ser o email


            stmt = conn.prepareStatement("INSERT INTO Loan (loanBooks, memberId, dateLoan, returnDate, stateLoan, taxFine) VALUES ( ?, ?, ?, ?, ?, ?)");

            if(bookSelectByISBN.getId() != 0){
                stmt.setInt(1, bookSelectByISBN.getId());
            }

            if(memberSelectById.getId() != 0){
                stmt.setInt(2, memberSelectById.getId());
            }
            stmt.setDate(3, java.sql.Date.valueOf(loan.getDateLoan()));
            stmt.setDate(4, java.sql.Date.valueOf(loan.getReturnDate()));
            stmt.setString(5, loan.getStateLoan().name());
            stmt.setDouble(6, loan.getTaxFine());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe loan was saved successfully");
            }
        }
        catch(SQLException e){
            throw new DaoException("There was a error as try save the loan: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
        }
    }

    @Override
    public Loan selectLoanByMember(Member member) {
        return null;
    }

    @Override
    public Loan selectLoanByStatus(StatusLoan status) {
        return null;
    }
}
