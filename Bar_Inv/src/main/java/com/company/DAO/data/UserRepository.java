package com.company.DAO.data;

import com.company.DAO.models.User;
import com.company.DAO.utils.ConnectionUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements Repository<User, Integer>{
    private ConnectionUtils connectionUtils;
    public UserRepository(ConnectionUtils connectionUtils) {
        if(connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    @Override
    public User findByID(Integer integer) {
        return null;
    }

    @Override
    public List<User> findAll() {
        Connection conn = null;
        List<User> users = new ArrayList();
        try {
            conn = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();
            String sqlQuery = "Select username, pass from " + schemaName + ".userandpw";
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sqlQuery);



        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            return users;
        }
    }

    @Override
    public Integer save(User obj) {
        return null;
    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public void updateByID(Integer integer) {

    }

    @Override
    public void addThing(User obj, Integer integer) {

    }
}
