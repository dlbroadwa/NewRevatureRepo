package com.Project0.dao;

import com.Project0.application.App;
import com.Project0.model.User;
import com.Project0.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl_DB implements UserDAO {
    private ConnectionUtil connectionUtil;

    public UserDAOImpl_DB(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public void loginUser(String username, String password, App app) throws Exception {

        Connection con = null;
        PreparedStatement stmt = null;
        String schemaName = connectionUtil.getDefaultSchema();
        List<User> users = new ArrayList<>();

        try {
            con = connectionUtil.getConnection();
            if(con != null) {
                String sql = "SELECT * FROM public.user WHERE name = ? AND password = ?";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);

                //send to DB & apply result
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    String uname = rs.getString("name");
                    String upass = rs.getString("password");
                    String uaccess = rs.getString("accesslevel");
                    User temp = new User(uname, upass,uaccess);
                    users.add(temp);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if(users.size() > 1 || users.size() <= 0) {
            throw new Exception("NUMBER OF USERS RETURNED FROM QUERY ERROR");
        }
        else {
            if(users.get(0).getUsername().equals(username) && users.get(0).getPassword().equals(password) ) {
                app.setUser(users.get(0));
            }
            else {
                System.out.printf("READ: %s  %s", users.get(0).getUsername(), users.get(0).getPassword());
                System.out.printf("PASSED: %s  %s", username, password);
                throw new Exception("ERROR LOGGING IN");
            }
        }

        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        String url = "jdbc:postgresql://database-1.cdr3hmlqxdcv.us-east-2.rds.amazonaws.com:5432/project0";
//        String user = "postgres";
//        String pass = "Cavalier93!";
//        Connection con = null;
//        PreparedStatement stmt = null;
//        boolean result = false;
//
//
//        //connect to the DB
//        try {
//            con = DriverManager.getConnection(url, user, pass);
//            //System.out.println("CONNECTION SUCCESSFUL");
//            //DB read statement
//            if(con != null) {
//                String sql = "SELECT * FROM public.users WHERE name = ? AND password = ?";
//                stmt = con.prepareStatement(sql);
//                stmt.setString(1, username);
//                stmt.setString(2, password);
//
//                //send to DB & apply result
//                ResultSet rs = stmt.executeQuery();
//                while(rs.next()) {
//
//                }
//                result = stmt.executeQuery();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        } finally {
//            try {
//                con.close();
//                System.out.println("CLOSED CONNECTION");
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
    }

    @Override
    public void changeUserPassword(User user, String newHashedPassword, App app) throws Exception {

    }
}
