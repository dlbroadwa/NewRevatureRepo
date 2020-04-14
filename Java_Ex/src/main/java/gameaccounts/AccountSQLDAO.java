package gameaccounts;

import java.util.ArrayList;
import java.sql.*;

public class AccountSQLDAO implements DAO{
    Connection con;
    Statement stmt;
    public AccountSQLDAO(){
        try {
            Class.forName("Resources/postgresql-42.2.12.jar");
            con=DriverManager.getConnection("dyltrashs2.crxekrgyc1qs.us-east-2.rds.amazonaws.com",
                    "dyltra","SHS#367785");
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void list() {
        try {
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from AccountList");
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
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
