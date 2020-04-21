package com.ex;
import java.sql.*;

public class Main {
    // Register the Driver with the Driver Manager
    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
    public static void main(String [] args){





//                   // jdbc:vendorname://hostname:port/databasename
//        String url = "jdbc:postgresql://apr06-2004-java.c9ympmujpozd.us-east-1.rds.amazonaws.com:5432/postgres";
//        String username = "gregcox";
//        String password = "Fuck4FUn2535!";
//        Connection connection = null;
//
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            if(connection != null) {
//                System.out.println("Connected to the database");
//                // Select all of the creators from the database
//                String sqlStr = "SELECT * from public.users";
//                Statement statement = connection.createStatement();
//
//                // execute the statement and retrieve a ResultSet
//                ResultSet rs = statement.executeQuery(sqlStr);
//
//                while(rs.next()) {
//                    System.out.println("User num " + rs.getString("id") + " Email address " + rs.getString("email"));
//                }
//
//            } else {
//                System.out.println("Connection failed");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if(connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                    // this will only happen if the connection has already been closed
//                    e.printStackTrace();
//                }
//            }
//        }
    }
}
