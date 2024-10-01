package dao;

import dao.servicesDAO.AuthorServicesDAO;
import model.entities.Author;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static dao.ConnectionFactory.getConnection;

public class AuthorDAO implements AuthorServicesDAO {

    private Connection conn = getConnection();

    @Override
    public Author authorFindById() {
        return null;
    }

    @Override
    public Author authorFindByName() {
        return null;
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
