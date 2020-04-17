package com.ex;

import com.ex.app.Application;
import com.ex.app.LibraryApp;
import com.ex.utils.DatabaseConnection;
import com.ex.utils.PostgreSQLConnection;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://database-1.c7zjtw5vhjwr.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = System.getenv("LIBRARY_ADMIN_USER");
        String password = System.getenv("LIBRARY_ADMIN_PASSWORD");
        //System.out.printf("user = %s, password = %s%n", username, password);
        /*String username = "library_admin";
        String password = "my$ecurep@ssw0rd";*/
        DatabaseConnection connection = new PostgreSQLConnection(url, username, password, "project0");

        if (!connection.isDriverInitialized()) {
            System.err.println("Failed to initialize database driver!");
            return;
        }

        Application app = new LibraryApp(connection);
        app.run();
    }

    /*static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void testSelect(Connection connection) {
        String sqlStr = "SELECT card_number, first_name, last_name FROM project0.users";
        try (Statement statement = connection.createStatement()) {
            // Execute
            ResultSet rs = statement.executeQuery(sqlStr);
            while (rs.next()) {
                System.out.printf("Barcode: %d Name: %s %s%n", rs.getInt("card_number"),
                        rs.getString("first_name"), rs.getString("last_name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String url = "jdbc:postgresql://database-1.c7zjtw5vhjwr.us-east-2.rds.amazonaws.com:5432/postgres";
        String username = "library_admin";
        String password = "my$ecurep@ssw0rd";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Connnected to database!");

                // Example select statement
                testSelect(connection);
            }
            else
                System.err.println("Connection failed!");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }*/
}
