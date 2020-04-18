package com.proj.data;

import com.proj.models.User;
import com.proj.utils.ConnectionUtils;

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
    public void save(User obj) {
        Connection connection = null;

        try{
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "insert into " + schemaName + ".users (username, password) values " +
                    "( '" + obj.getNewUsername() + "', '" + obj.getNewPassword() + "')";
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
