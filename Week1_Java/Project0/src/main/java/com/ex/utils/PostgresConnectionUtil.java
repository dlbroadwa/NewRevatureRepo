package com.ex.utils;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PostgresConnectionUtil extends ConnectionUtils {

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public PostgresConnectionUtil() {
        this.defaultSchema = "public";
    }

    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;
    }

    public PostgresConnectionUtil(Properties properties) {
    }

    public PostgresConnectionUtil(FileReader propsInput) {

    }


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}