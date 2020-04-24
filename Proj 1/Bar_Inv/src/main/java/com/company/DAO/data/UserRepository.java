package com.company.DAO.data;

import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, String, String>{
    private ConnectionUtils connectionUtils;
    public UserRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public User findByID(String s) { //take a username (input) and search for it on the userandpw table
        Connection conn = null;
        User oneUser = new User();
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select username, pass from " + schemaName + ".userandpw where username='"+ s + "'";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlQuery);
            rs.next();
            oneUser.setUserName(rs.getString("username"));
            oneUser.setPassword(rs.getString("pass"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return oneUser;                        //return the user with username "s"
        }
    }
    @Override
    public List<User> findAll() {
        //should give us the whole list of users and passwords as a list of user objects
        Connection conn = null;
        List<User> users = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            //we will look for every entry on the userandpw table, except the admin... cuz only the admin will see this list, and they know who they are
            String sqlQuery = "Select username, pass from " + schemaName + ".userandpw where username != 'admin'";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sqlQuery);

            while(rs.next()){ //iterate through the result set
                String username = rs.getString("username");
                String pass = rs.getString("pass");

                User tmp = new User();
                tmp.setUserName(username);
                tmp.setPassword(pass);

                users.add(tmp); //add each user to the list
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            return users; //return all the user objects
        }
    }


    @Override
    public void save(User obj) {
        // take a user object and add it to the userandpw table
        Connection conn = null;
        try{
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into "+schemaName+".userandpw (pass, username ) values ('"+obj.getPassword()+"','"+obj.getUserName()+"')";
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }


    //won't use any of the following methods:
    @Override
    public void deleteByID(String s) {

    }
    @Override
    public void updateByID(User user) {

    }

    @Override
    public List<User> compareColumns(String s1, String s2, String s3) {
        return null;
    }

    @Override
    public List<User> findAllForName(String s) throws SQLException {
        return null;
    }

}
