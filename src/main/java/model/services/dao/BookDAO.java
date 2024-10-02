package model.services.dao;

import exceptions.DaoException;
import model.entities.Book;
import model.entities.Loan;

import java.sql.*;

import static model.services.dao.ConnectionFactory.getConnection;

public class BookDAO implements model.repositories.dao.BookServicesDAO {

    private Connection conn = getConnection();


    @Override
    public void insertBook(Book book) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO Books (title, author, isbn, gender, quantity) VALUES (?, ?, ?, ?, ?)");
            stmt.setString(1, book.getTitle());

            stmt.setInt(2, 1); //Arrumar a lógica para referenciar outra tabela
            stmt.setFloat(3, book.getIsbn());
            stmt.setString(4, book.getGenre());
            stmt.setInt(5, book.getQuantity());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe book was saved successfully");
            }
        }
        catch(SQLException e){
            throw new DaoException("There was a error as try save the book: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
        }
    }

    @Override
    public Book findBookByISBN(Long isbn) {
        return null;
    }

    @Override
    public void insertLoanBook(Loan loan) {

    }

    @Override
    public void updateReturnLoan(Loan loan) {

    }
}
