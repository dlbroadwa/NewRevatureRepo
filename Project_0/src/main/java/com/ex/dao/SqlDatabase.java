package com.ex.dao;

import com.ex.main.Runner;
import com.ex.main.Screen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDatabase extends Runner{

    static{
        try{
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String url = "jdbc:postgresql://database-1.cb402pxtppo6.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "paityn";
        String password = "revature";
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to the database");
            } else {
                System.out.println("Connection Failed");
            }
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
