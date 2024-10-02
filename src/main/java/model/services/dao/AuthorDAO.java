package model.services.dao;

import exceptions.DaoException;
import model.repositories.dao.AuthorRepositoryDAO;
import model.entities.Author;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.services.dao.ConnectionFactory.getConnection;

public class AuthorDAO implements AuthorRepositoryDAO {

    private Connection conn = getConnection();
    private Author author = null;

    @Override
    public Author selectAuthorById(int id) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Author WHERE idAuthor = ?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if(rs.next()){
                int idAuthor = rs.getInt("idAuthor");
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String nationality = rs.getString("nationality");
                String biography = rs.getString("biography");

                author = new Author(name, idAuthor,dateOfBirth, nationality, biography);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return author;
    }

    @Override
    public List<Author> selectAuthorByName(String authorName) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Author> authorList = new ArrayList<>();
        try{
            stmt = conn.prepareStatement("SELECT * FROM Author WHERE name=?");
            stmt.setString(1, authorName);
            rs = stmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("idAuthor");
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String nationality = rs.getString("nationality");
                String biography = rs.getString("biography");

                author = new Author(name, id, dateOfBirth, nationality, biography);
                authorList.add(author);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return authorList;
    }

    @Override
    public List<Author> selectAllAuthor() {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Author> authorsList = new ArrayList<>();
        try{
            stmt = conn.prepareStatement("SELECT * FROM Author");
            rs = stmt.executeQuery();

            while(rs.next()){
                String name = rs.getString("name");
                LocalDate dateOfBirth = rs.getDate("dateOfBirth").toLocalDate();
                String nationality = rs.getString("nationality");
                String biography = rs.getString("biography");

                author = new Author(name, dateOfBirth, nationality, biography);
                authorsList.add(author);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return authorsList;
    }

    @Override
    public void insertAuthor(Author author) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("INSERT INTO Author (name, dateOfBirth, nationality, Biography) VALUES (?, ?, ?, ?)");
            stmt.setString(1, author.getName());
            stmt.setDate(2, java.sql.Date.valueOf(author.getDateOfBirth()));
            stmt.setString(3, author.getNationality());
            stmt.setString(4, author.getBiography());

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe author was saved successfully");
            }
        }
        catch(SQLException e){
            throw new DaoException("There was a error as try save the new author: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
        }
    }
}
