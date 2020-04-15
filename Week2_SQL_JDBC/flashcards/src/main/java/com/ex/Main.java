package com.ex;

import com.ex.clients.CreatorService;
import com.ex.data.CreatorSQLRepository;
import com.ex.data.Repository;
import com.ex.models.Creator;
import com.ex.utils.ConnectionUtils;
import com.ex.utils.PostgresConnectionUtil;

import java.sql.*;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    ConnectionUtils connectionUtils = new PostgresConnectionUtil(
      "jdbc:postgresql://apr06-2004-java.ccadpnws7azu.us-west-1.rds.amazonaws.com:5432/postgres",
      "flashcards_user", "p@$$w0rd123", "flashcards");
    Repository<Creator, Integer> creatorRepo = new CreatorSQLRepository(connectionUtils);
    CreatorService service = new CreatorService(creatorRepo);

    List<Creator> allCreators = service.getAllCreators();

    for(Creator c : allCreators) {
      System.out.println("Creator " + c.getCreatorName());
    }
  }













//  // Register the Driver with the Driver Manager
//  static {
//    try {
//      DriverManager.registerDriver(new org.postgresql.Driver());
//    } catch (SQLException throwables) {
//      throwables.printStackTrace();
//    }
//  }
//
//  public static void main(String[] args) {
//                  // jdbc:vendorname://hostname:port/databasename
//    String url = "jdbc:postgresql://apr06-2004-java.ccadpnws7azu.us-west-1.rds.amazonaws.com:5432/postgres";
//    String username = "flashcards_user";
//    String password = "p@$$w0rd123";
//    Connection connection = null;
//
//    try {
//      connection = DriverManager.getConnection(url, username, password);
//      if(connection != null) {
//        System.out.println("Connected to the database");
//        // Select all of the creators from the database
//        String sqlStr = "SELECT id, creator_name from flashcards.creator";
//        Statement statement = connection.createStatement();
//
//        // execute the statement and retrieve a ResultSet
//        ResultSet rs = statement.executeQuery(sqlStr);
//
//        while(rs.next()) {
//          System.out.println("Creator  " + rs.getString("creator_name"));
//        }
//
//      } else {
//        System.out.println("Connection failed");
//      }
//    } catch (SQLException throwables) {
//      throwables.printStackTrace();
//    } finally {
//      if(connection != null) {
//        try {
//          connection.close();
//        } catch (SQLException throwables) {
//          // this will only happen if the connection has already been closed
//          throwables.printStackTrace();
//        }
//      }
//    }
//  }
}
