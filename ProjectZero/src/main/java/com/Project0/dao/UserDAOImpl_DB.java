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
    public User loginUser(String username, String password, App app) throws Exception {

        User user = null;
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
                user =  users.get(0);
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
        } finally{
            return user;
        }
    }

    @Override
    public boolean changeUserPassword(User user, String newHashedPassword, App app) throws Exception {
        Connection con = null;
        PreparedStatement stmt = null;
        String schemaName = connectionUtil.getDefaultSchema();
        String oldPass = user.getPassword();
        boolean success = false;

        try {
            con = connectionUtil.getConnection();
            if(con != null) {
                String sql = "UPDATE public.user SET password = ? WHERE name = ? AND password = ?";
                stmt = con.prepareStatement(sql);
//                stmt.setString(1, schemaName);
                stmt.setString(1, newHashedPassword);
                stmt.setString(2, user.getUsername());
                stmt.setString(3, user.getPassword());

                System.out.printf("SQL LINE: %s", stmt.toString());
                //send to DB & apply result
                success = stmt.executeUpdate() > 0;
                app.setPassword(success ? newHashedPassword : oldPass);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            return success;
        }
    }
}
