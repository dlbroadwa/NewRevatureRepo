package com.company.DAO.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnectionUtils extends ConnectionUtils {
    //register the driver
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public PostgresqlConnectionUtils() {
        this.schema = "public";
    }

    public PostgresqlConnectionUtils(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.schema = schema;
    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
