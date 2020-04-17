package com.ex.app;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    static Connection connection;

    public static void main(String[] args) {
        try {
            java.sql.DriverManager.registerDriver(new org.postgresql.Driver());
            connection = java.sql.DriverManager.getConnection(
                    "jdbc:postgresql://dbinstance1.c2b26c4tx3es.us-east-2.rds.amazonaws.com:5432/db1",
                    "davide", "jw8s0F4");
            connection.toString();
//            System.out.println("Connected");
//            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            String schemaName = "assistant";
            //String sql = "Select id, creator_name from " + schemaName + ".creator";
            String sql = "Select * from assistant.\"Item\"";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                System.out.println(rs.toString());
                System.out.println(rs.getInt("id"));
                System.out.println(rs.getString("name"));
                System.out.println(rs.getDouble("value"));
                System.out.println(rs.getShort("shelfLife"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


//        Application application = new InventoryAssistant();
//        application.run();
    }
}
