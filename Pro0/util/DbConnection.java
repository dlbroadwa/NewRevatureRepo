/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of the DbConnection is to manage the connection 
 **between the database and the application
 **
 **Date created: 4/16/20
 **
 **Current Version: 1.2
 **
 **Date last updated: 4/16/20
 **
 **Last Change: added a method to close the connection when done
 **
 **/

package util;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.SQLException;

public class DbConnection {
	
private final static String url = "jdbc:postgresql://dbase.c9bsvowbng02.us-east-1.rds.amazonaws.com:5432/postgres";
	
	private final static String user = "mabel";
	
	private final static String password = "M@belRulez1";
	
	static Connection conn = null;
	
	 public DbConnection() {
	        
		 	try {
	        
		 		conn = DriverManager.getConnection(url, user, password);
	       
	        } catch (SQLException e) {
	            
	        	System.out.println(e.getMessage());
	            
	        }

		 	
	        returnConn(conn);
	        
	    }

	private static Connection returnConn(Connection conn) {
		// TODO Auto-generated method stub
		
		return conn;
		
	}
	 
	protected void closeConnection() {
		
		try {
			
			conn.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		
		}
		
	}

}
