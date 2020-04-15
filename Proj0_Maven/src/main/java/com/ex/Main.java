package com.ex;

import java.sql.*;

public class Main {

    static {
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
    }
}
