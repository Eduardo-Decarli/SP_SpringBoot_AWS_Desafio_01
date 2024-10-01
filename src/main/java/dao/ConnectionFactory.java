package dao;

import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection conn = null;

    public static Connection getConnection(){
        if(conn == null){
            try(FileInputStream fs = new FileInputStream("db.properties")){
                Properties props = new Properties();
                props.load(fs);

                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            }
            catch(IOException e){
                throw new DaoException(e.getMessage());
            }
            catch (SQLException e){
                throw new DaoException(e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection(){
        if(conn != null){
            try {
                conn.close();
            }
            catch(SQLException e){
                throw new DaoException(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st){
        if(st != null){
            try{
                st.close();
            }
            catch (SQLException e){
                throw new DaoException(e.getMessage());
            }
        }
    }

    public static void closePreparedStatement(PreparedStatement stmt){
        if(stmt != null){
            try{
                stmt.close();
            }
            catch (SQLException e){
                throw new DaoException(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs != null){
            try{
                rs.close();
            }
            catch(SQLException e){
                throw new DaoException(e.getMessage());
            }
        }
    }

}

