package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import classes.*;
import util.DbConnection;

public class DatabaseManager extends DbConnection{
	
	DbConnection connection;
	
	DatabaseManager(){
		
		connection = new DbConnection();
		
	}
	
	
	public ArrayList<Clothing> getResult(String search) {
		
		connection.startConnection();
		
		ArrayList<Clothing> clothes = new ArrayList<Clothing>();
		
		 System.out.println("Searching........");
		 
	      Statement stmt;
	      
		try {
			
			stmt = conn.createStatement();
			
			String sql = "SELECT itemNum, itemType, itemSize, color, numberof, price FROM inventory WHERE " + search;

			 ResultSet rs = stmt.executeQuery(sql);
				
		      while(rs.next()){
		    	 
		    	  int itemNum = rs.getInt("itemNum");
		    	  
		    	  String id  = rs.getString("itemType");
		    	  
		    	  String size  = rs.getString("itemSize");
		    	  
		    	  String color = rs.getString("color");
		    	  
		    	  int numOf = rs.getInt("numberof");
		    	  
		    	  double price = rs.getDouble("price");
		    	  
		    	  System.out.println("Item: " + id + " Size: " + size + " Color: " + color + 
		    			  " We have: " + numOf + " for: $" + price + " each.");
		    	  
		      }
		     
		      connection.closeConnection();
		      
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		return clothes;
		
	}	

}
