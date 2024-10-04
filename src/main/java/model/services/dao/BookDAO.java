package model.services.dao;

import exceptions.DaoException;
import model.entities.Author;
import model.entities.Book;
import model.repositories.dao.BookRepositoryDAO;
import model.services.AuthorServices;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
                System.out.println("\n=====================");
                System.out.println(" The book was saved successfully");
                System.out.println("=====================\n");
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
                book.setId(idBook);
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
    public List<Book> selectBooksByAuthor(int idAuthor) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Book> listBooks = new ArrayList<>();

        try{
            stmt = conn.prepareStatement("SELECT * FROM Books WHERE author = ?");
            stmt.setInt(1, idAuthor);
            rs = stmt.executeQuery();

            while(rs.next()){
                int idBook = rs.getInt("idBooks");
                String title = rs.getString("title");
                int authorId = rs.getInt("author");
                LocalDate datePublication = rs.getDate("datePublication").toLocalDate();
                long isbnBook = rs.getLong("isbn");
                String gender = rs.getString("gender");
                int quantity = rs.getInt("quantity");

                AuthorServices authorServices = new AuthorServices(new AuthorDAO());
                Author author = authorServices.findAuthorById(authorId);

                book = new Book(title, author, datePublication, isbnBook, gender, quantity);
                book.setId(idBook);
                listBooks.add(book);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the book: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return listBooks;
    }

    @Override
    public List<Book> selectAllBooks() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Book> listBooks = new ArrayList<>();
        try{
            stmt = conn.prepareStatement("SELECT * FROM Books");
            rs = stmt.executeQuery();

            while(rs.next()){
                int IdBook = rs.getInt("idBooks");
                String title = rs.getString("title");
                int authorId = rs.getInt("author");
                LocalDate datePublication = rs.getDate("datePublication").toLocalDate();
                long isbn = rs.getLong("isbn");
                String gender = rs.getString("gender");
                int quantity = rs.getInt("quantity");

                AuthorServices authorServices = new AuthorServices(new AuthorDAO());
                Author author = authorServices.findAuthorById(authorId);

                book = new Book(title, author, datePublication, isbn, gender, quantity);
                listBooks.add(book);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return listBooks;
    }

    @Override
    public void updateBookMinusQT(int idBook, int quantity) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Books SET quantity = quantity - ? WHERE (idBooks = ?)");
            st.setInt(1, quantity);
            st.setInt(2, idBook);

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe update was complete");
            }
        }
        catch(SQLException e){
            throw new DaoException("Error to change book: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(st);
        }
    }

    @Override
    public void updateBookPlusQT(int idBook, int quantity) {
        PreparedStatement st = null;
        try{
            st = conn.prepareStatement("UPDATE Books SET quantity = quantity + ? WHERE (idBooks = ?)");
            st.setInt(1, quantity);
            st.setInt(2, idBook);

            int rowsAffected = st.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe update was complete");
            }
        }
        catch(SQLException e){
            throw new DaoException("Error to change book: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(st);
        }
    }

    @Override
    public Book findBookById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Book book = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Books WHERE idBooks = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                int idBook = rs.getInt("idBooks");
                String title = rs.getString("title");
                int authorId = rs.getInt("author");
                LocalDate datePublication = rs.getDate("datePublication").toLocalDate();
                long isbn = rs.getLong("isbn");
                String gender = rs.getString("gender");
                int quantity = rs.getInt("quantity");

                AuthorDAO authorDao = new AuthorDAO();
                Author author = authorDao.selectAuthorById(authorId);
                book = new Book(title, author,datePublication, isbn, gender, quantity);
                book.setId(idBook);
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
}

