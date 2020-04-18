package BankApp.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**************************************************
 *
 * SqlDAO class to setup connection to database
 * and also close connection to database
 *
 * Methods:
 *
 * connect - connect to the database
 * close - methods to close ResultSet, connection, and Statement
 *************************************************/

public class SqlDAO{

    //declare database credentials
    private final String jdbcUrl = System.getenv("jdbcUrl") ;
    private final String dbUser = System.getenv("dbUser");
    private final String dbPassword = System.getenv("dbPassword");


    //Connect to the PostgreSQL database
    public Connection connect() {
        Connection conn = null;
        try {

            conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }


    //close connection, release resources, close result set
    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement s) {
        try {
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close(Statement st, Connection con) {
        close(st);
        close(con);
    }

    public static void close(ResultSet rs, Statement st, Connection con) {
        close(rs);
        close(st);
        close(con);
    }

}