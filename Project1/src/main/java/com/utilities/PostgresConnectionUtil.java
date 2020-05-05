package com.utilities;

import javafx.application.Application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnectionUtil extends ConnectionUtils {
    protected Connection connection = null;

    public PostgresConnectionUtil(String url, String username, String password, String schema) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.defaultSchema = schema;

        testConnection();
    }
    public void testConnection(){
        try {
            // tests if database is connected
            connection = getConnection();
            if(connection != null) {

                //call the application
               // Application app = new InventoryManagementApp(connection);
                //app.run();

            } else {
                System.out.println("Connection failed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // if connection worked close it
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    // this will only happen if the connection has already been closed
                    throwables.printStackTrace();
                }
            }
        }
    }
    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
