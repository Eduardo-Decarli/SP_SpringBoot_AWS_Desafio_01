package model.services.dao;

import exceptions.DaoException;
import model.entities.Author;
import model.entities.Book;
import model.entities.Loan;
import model.repositories.dao.BookRepositoryDAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static model.services.dao.ConnectionFactory.getConnection;

public class BookDAO implements BookRepositoryDAO {

    private Connection conn = getConnection();
    private Book book = null;


    @Override
    public void insertBook(Book book) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("INSERT INTO Books (title, author, datePublication, isbn, gender, quantity) VALUES (?, ?, ?, ?, ?, ?)");

            stmt.setString(1, book.getTitle());
            stmt.setInt(2, book.getAuthor().getId());
            stmt.setDate(3, java.sql.Date.valueOf(book.getDatePlublication()));
            stmt.setFloat(4, book.getIsbn());
            stmt.setString(5, book.getGenre());
            stmt.setInt(6, book.getQuantity());

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
    public Book selectBookByIsbn(long isbn) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try{
            stmt = conn.prepareStatement("SELECT * FROM Books WHERE isbn = ?");
            stmt.setLong(1, isbn);
            rs = stmt.executeQuery();

            if(rs.next()){
                int idBook = rs.getInt("idBooks");
                String title = rs.getString("title");
                int authorId = rs.getInt("author");
                LocalDate datePublication = rs.getDate("datePublication").toLocalDate();
                long isbnBook = rs.getLong("isbn");
                String gender = rs.getString("gender");
                int quantity = rs.getInt("quantity");

                Author author = new AuthorDAO().selectAuthorById(authorId);

                book = new Book( title, author, datePublication, isbnBook, gender, quantity);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the book: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return book;
    }

    @Override
    public Book selectBookByName(int id) {
        return null;
    }

    @Override
    public List<Book> selectBooksByAuthor(Author author) {
        return List.of();
    }
}
