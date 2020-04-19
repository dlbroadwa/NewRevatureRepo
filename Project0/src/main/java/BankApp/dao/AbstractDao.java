package BankApp.dao;

import BankApp.jdbc.BankAppConnection;
import BankApp.jdbc.impl.PostgreSQLConnection;
import BankApp.utils.BankAppStrings;
import BankApp.utils.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractDao {

    BankAppConnection connection = ConnectionFactory.getConnection(BankAppStrings.POSTGREE_SQL);

    public Connection connect(){
        return  connection.connect();
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
