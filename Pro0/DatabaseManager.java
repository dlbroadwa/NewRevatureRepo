/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of the Database is to deny direct access
 **to DbConnection
 **
 **Date created: 4/16/20
 **
 **Current Version: 1.2
 **
 **Date last updated: 4/15/20
 **
 **Last Change: Added a method to query the database
 **
 **/

package Services;

import util.DbConnection;

public class DatabaseManager extends DbConnection{
	
	private DbConnection connection;
	
	DatabaseManager(){
		
		connection = new DbConnection();
		
	}
	
	
	public void searchFor(String search) {
		
	
	}
	
}
