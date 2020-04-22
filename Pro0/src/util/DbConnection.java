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
	
	/*
	 * 
	 * These fields are private and final in an attempt to keep them from changing and from allowing
	 * other classes to interfere
	 * 
	 */
	
	private final static String url = "jdbc:postgresql://dbase.c9bsvowbng02.us-east-1.rds.amazonaws.com:5432/postgres";
	
	private final static String user = "mabel";
	
	private final static String password = "M@belRulez1";
	
	protected static Connection conn = null;
	
	//Invisible constructor DbConnection()
	 
	 public void startConnection(){
		 
		 //This method attempts to start the connection
	        
		 	try {
	        
		 		conn = DriverManager.getConnection(url, user, password); 
		 		
		 		//Attempts to get connection using the url,user, and password left
	       
	        } //end of Try{}
		 	
		 	catch (SQLException e) {
	            
		 		//This will catch any SQLException
		 		
	        	System.out.println(e.getMessage());//This will display the message to the user
	            
	        }//end of catch(SQLException e)
	        
	    }//end of startConnection()
	 
	public void closeConnection() {
		
		//Attempts to close the connection
		
		try {
			
			//calls the close method of conn
			
			conn.close();
			
		} //end of try{}
		
		catch (SQLException e) {
		
			//This will catch any SQLException
			
			e.printStackTrace(); //This will print the stacktrace from the exception
		
		}//end of catch(SQLException e)
		
	}//end of closeConnection()

}//end of DbConnection
