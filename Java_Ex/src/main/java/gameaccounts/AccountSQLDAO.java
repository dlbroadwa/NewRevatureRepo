package gameaccounts;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class AccountSQLDAO implements DAO{
    private final String url = "jdbc:postgresql://dyltrashs2.crxekrgyc1qs.us-east-2.rds.amazonaws.com/";
    private final String user = "dyltra";
    private final String password = "password";

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException  e) {
            e.printStackTrace();
        }

        return conn;
    }

    @Override
    public void list() {
        try {
            Connection con = connect();
            Statement stmt = con.createStatement();

            //ResultSet rs = stmt.executeQuery("select * from dog");
            //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccount(String name, String password, boolean isAdmin) {

    }

    @Override
    public void deleteAccount(String name, String path) {

    }

    @Override
    public void updateAccount(ArrayList<String> text, String path) {

    }
}
