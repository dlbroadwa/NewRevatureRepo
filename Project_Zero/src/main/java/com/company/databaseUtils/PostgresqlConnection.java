package com.company.databaseUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresqlConnection {
    private String url;
    private String username;
    private String password;
    private String defaultSchema;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PostgresqlConnection(String url, String username, String password, String defaultSchema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = defaultSchema;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public String getDefaultSchema() {
        return defaultSchema;
    }
}
