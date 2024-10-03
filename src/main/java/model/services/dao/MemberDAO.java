package model.services.dao;

import exceptions.DaoException;
import model.entities.Author;
import model.entities.Member;
import model.repositories.dao.MemberRepositoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static model.services.dao.ConnectionFactory.getConnection;

public class MemberDAO implements MemberRepositoryDAO {
    Connection conn = getConnection();

    @Override
    public void insertMember(Member member) {
        PreparedStatement stmt = null;
        try {

            stmt = conn.prepareStatement("INSERT INTO Member (name, address, phoneNumber, email, dateAssociation) VALUES (?, ?, ?, ?, ?)");

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getAddress());
            stmt.setFloat(3, member.getPhoneNumber());
            stmt.setString(4, member.getEmail());
            stmt.setDate(5, java.sql.Date.valueOf(member.getDateAssociation()));

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0){
                System.out.println("\nThe member was saved successfully");
            }
        }
        catch(SQLException e){
            throw new DaoException("There was a error as try save the member: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
        }
    }

    @Override
    public Member selectMemberByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Member member = null;
        try{
            stmt = conn.prepareStatement("SELECT * FROM Member WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if(rs.next()){
                int idMember = rs.getInt("idMember");
                String name = rs.getString("name");
                String address = rs.getString("address");
                long phoneNumber = rs.getLong("phoneNumber");
                String emailMember = rs.getString("email");
                LocalDate dateAssociation = rs.getDate("dateAssociation").toLocalDate();

                member = new Member(name, address, phoneNumber, emailMember, dateAssociation);
            }
        }
        catch(SQLException e){
            throw new DaoException("Error finding the Member: " + e.getMessage());
        }
        finally {
            ConnectionFactory.closePreparedStatement(stmt);
            ConnectionFactory.closeResultSet(rs);
        }
        return member;
    }
}
