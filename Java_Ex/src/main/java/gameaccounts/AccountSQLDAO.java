package gameaccounts;

import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;


public class AccountSQLDAO implements DAO{
    public static ArrayList<Account> accountList;
    private final String url = "jdbc:postgresql://dyltrashs.crxekrgyc1qs.us-east-2.rds.amazonaws.com:5432/dyltrashs";
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
            String list = "select * from public.accountlist";
            Statement stmt = con.createStatement();

            ResultSet rs = stmt.executeQuery(list);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createAccount(String name, String password, boolean isAdmin) {
        try {
            Connection con = connect();
            String list = "insert into public.accountlist (username, password, IsAdmin) values('"+name+"','"+password
                    +"',"+isAdmin+");";
            Statement stmt = con.createStatement();

            stmt.executeUpdate(list);

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    @Override
    public void deleteAccount(String name) {
        try {
            Connection con = connect();
            String list = "delete from public.accountlist where username = "+"'"+name+"';";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(list);
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAccount(ArrayList<String> text) {

    }
}
