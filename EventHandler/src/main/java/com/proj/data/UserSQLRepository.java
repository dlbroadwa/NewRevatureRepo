package com.proj.data;


import com.proj.models.User;
import com.proj.utils.ConnectionUtils;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserSQLRepository implements Repository<User, String> {
    private ConnectionUtils connectionUtils;

    public UserSQLRepository(ConnectionUtils connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }


    @Override
    public User findById(String s) {
        return null;
    }


    @Override
    public String save(User userObj) throws SQLException {
        Connection connection = null;
        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into " + schemaName + ".users (username, password) values " +
                    "( '" + userObj.getNewUsername() + "', '" + userObj.getNewPassword() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (PSQLException e) {
            System.out.println("Username already taken try another.");

        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    @Override
    public void update(User newObj, String s) {

    }



        @Override
    public void delete(User newObj) {

    }


    @Override
    public List<User> findAll() {
        return null;
    }
}
