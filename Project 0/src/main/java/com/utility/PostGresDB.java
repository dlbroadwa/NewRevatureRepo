package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostGresDB extends DBConnection{

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public PostGresDB () {
        this.schema = "public";
    }

    public PostGresDB (String url, String schema) {
        this.url = url;
        this.schema = schema;
    }
    @Override
    public Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(url, schema);
    }
}
