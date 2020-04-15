package utils;

import java.sql.*;

public class Database {
    public Database()
    {
        ConnectionUtils connectionUtils = new PostgresConnectionUtil(
                "jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase",
                "jpragasa",
                "Lucario11495",
                "public");
    }
}










//ConnectionUtils connectionUtils = new PostgresConnectionUtil("jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase", "jpragasa", "Lucario11495", "public");



//    static
//    {
//        try
//        {
//            DriverManager.registerDriver(new org.postgresql.Driver());
//        }
//        catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    public Database()
//    {
//        String url = "jdbc:postgresql://database-1.cis8fsnxixal.us-east-1.rds.amazonaws.com:5432/myDatabase";
//        String username = "jpragasa";
//        String password = "Lucario11495";
//        Connection connection = null;
//        try
//        {
//            connection = DriverManager.getConnection(url, username, password);
//            if(connection != null)
//            {
//                System.out.println("Connected");
////                String sqlStr = "SELECT id, creator_name from flashcards.creator";
////                Statement statement = connection.createStatement();
//
////                execute the statement and retrieve a ResultSet
////                ResultSet rs = statement.executeQuery(sqlStr);
////                while(rs.next())
////                {
////                    System.out.println("Creator " + rs.getString("creator_name"));
////                }
//            }
//            else
//            {
//                System.out.println("Connection failed");
//            }
//        } catch (SQLException e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            if(connection != null)
//            {
//                try
//                {
//                    connection.close();
//                } catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

