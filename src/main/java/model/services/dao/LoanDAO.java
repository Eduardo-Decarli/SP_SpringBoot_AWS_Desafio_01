package model.services.dao;

import exceptions.DaoException;
import model.entities.Author;
import model.entities.Book;
import model.entities.Loan;
import model.entities.Member;
import model.entities.enumeration.StatusLoan;
import model.repositories.dao.LoanRepositoryDAO;
import model.services.AuthorServices;
import model.services.BookServices;
import model.services.LoanServices;
import model.services.MemberServices;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.services.dao.ConnectionFactory.getConnection;

public class LoanDAO implements LoanRepositoryDAO {

    Connection conn = getConnection();

    @Override
    public void insertLoan(Loan loan) {
        PreparedStatement stmt = null;

        try{
            BookServices bookServices = new BookServices(new BookDAO());
            Book bookSelectByISBN = bookServices.findBookByIsbn(loan.getBook().getIsbn());

            MemberServices memberServices = new MemberServices(new MemberDAO());
            Member memberSelectById = memberServices.findMemberByEmail(loan.getMember().getEmail());

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
            stmt.setBigDecimal(6, loan.getTaxFine());

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
    public List<Loan> selectLoanByStatus(StatusLoan status) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Loan> listLoan = new ArrayList<>();
        try{
            stmt = conn.prepareStatement("SELECT * FROM Loan WHERE stateLoan = ?");
            stmt.setString(1, status.name());
            rs = stmt.executeQuery();

            while(rs.next()){
                int idLoan = rs.getInt("idLoan");
                int loanBookId = rs.getInt("loanBooks");
                int memberId = rs.getInt("memberId");
                LocalDate dateLoan = rs.getDate("dateLoan").toLocalDate();
                LocalDate returnDate = rs.getDate("returnDate").toLocalDate();
                String stateLoan = rs.getString("stateLoan");
                double taxFine = rs.getDouble("taxFine");

                BookDAO bookDAO = new BookDAO();
                Book loanBook = bookDAO.findBookById(loanBookId);

                MemberDAO memberDAO = new MemberDAO();
                Member member = memberDAO.selectMemberById(memberId);

                Loan loan = new Loan(loanBook, member, dateLoan, returnDate, StatusLoan.valueOf(stateLoan), new BigDecimal(taxFine));
                loan.setId(idLoan);

                listLoan.add(loan);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return listLoan;
    }

    @Override
    public List<Loan> selectAllLoan() {
        return List.of();
    }

    @Override
    public Loan selectLoanById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Loan loan = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Loan WHERE idLoan = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                int loanId= rs.getInt("idLoan");
                int booksId = rs.getInt("loanBooks");
                int memberId = rs.getInt("loanBooks");
                LocalDate dateLoan = rs.getDate("dateLoan").toLocalDate();
                LocalDate dateReturn = rs.getDate("returnDate").toLocalDate();
                String loanStatus = rs.getString("stateLoan");
                double taxFine = rs.getDouble("taxFine");

                BookDAO bookDAO = new BookDAO();
                Book loanBook = bookDAO.findBookById(booksId);

                MemberDAO memberDAO = new MemberDAO();
                Member member = memberDAO.selectMemberById(memberId);

                loan = new Loan(loanBook, member ,dateLoan,dateReturn, StatusLoan.valueOf(loanStatus),new BigDecimal(taxFine));
                loan.setId(loanId);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the loan: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return loan;
    }

    @Override
    public void updateLoanCOMPLETE(int id) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Loan SET stateLoan = 'COMPLETE' WHERE (idLoan = ?)");
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe update was complete");
            }
        }
        catch(SQLException e){
            throw new DaoException("Error to change loan status: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(st);
        }
    }
}


