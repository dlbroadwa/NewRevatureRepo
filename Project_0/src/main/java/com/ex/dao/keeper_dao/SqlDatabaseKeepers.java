package com.ex.dao.keeper_dao;

import com.ex.main.Runner;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SqlDatabaseKeepers implements UserDAO<Keepers, String> {
    private Runner connectionUtils;

    public SqlDatabaseKeepers(Runner connectionUtils) {
        if (connectionUtils != null) {
            this.connectionUtils = connectionUtils;
        }
    }

    public List<Keepers> findAll(){

        Connection connection = null;
        List<Keepers> keepers = new ArrayList<>();

        try {
            connection = connectionUtils.getConnection();
            String schemaName = connectionUtils.getDefaultSchema();

            String sql = "Select username,password from " + schemaName + ".keepers";
                Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");

                Keepers temp = new Keepers();
                    temp.setUsernameKey(username);
                    temp.setPasswordKey(password);
                keepers.add(temp);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return keepers;
    }

}
