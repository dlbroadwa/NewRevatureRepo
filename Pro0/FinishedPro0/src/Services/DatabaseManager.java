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

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import util.DbConnection;

public class DatabaseManager extends DbConnection{
	
	private DbConnection connection;
	
	public DatabaseManager(){
		
		connection = new DbConnection();
		
	}
	
	public void searchFor(String search) {
		
		connection.startConnection();
		
		 System.out.println("Searching........");
		 
	      Statement stmt;
	      
		try {
			
			int counter = 1;
			
			ArrayList<Integer> itemNums = new ArrayList<>();
	
			boolean isShirt = false;
			
			stmt = conn.createStatement();
			
			String sql = "SELECT itemNum, itemType, itemSize, color, numberof, price FROM inventory WHERE " + search;
			
			if(search.equals("itemNum > 1000 AND itemNum < 2000")) {
				
				isShirt = true;
			}
			
			 ResultSet rs = stmt.executeQuery(sql);
			 
			 System.out.println("I found some matches! Would you like any of these?");
				
		      while(rs.next()){
		    	 
		    	  int itemNum = rs.getInt("itemNum");
		    	  
		    	  itemNums.add(itemNum);
		    	  
		    	  String id  = rs.getString("itemType"); 
		    	  
		    	  if(isShirt == true) {
		    		  
		    		  if(id.equals("l s shirt")) {
		    		  
		    			  id = "long-sleeved shirt";
		    		  }
		    	  
		    		  else if (id.equals("s s shirt")){
		    		  
		    			  id = "short-sleeved shirt";
		    		  }
		    	  
		    		  else if (id.equals("n s shirt")){
		    		  
		    			  id = "no-sleeved shirt";
		    		  }
		    	
		    	  }
		    	  
		    	  String size  = rs.getString("itemSize");
		    	  
		    	  String color = rs.getString("color");
		    	  
		    	  int numOf = rs.getInt("numberof");
		    	  
		    	  double price = rs.getDouble("price");
		    	  
		    	  System.out.println(counter++ + ": Item: " + id + " Size: " + size + " Color: " + color + 
		    			  " We have: " + numOf + " for: $" + price + " each.");
		    	  
		      }
		      
		      System.out.println(counter + ": No thank you");
		     
		      
		      
		      connection.closeConnection();
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
	  
	}
	
}
