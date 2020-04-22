package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import util.DbConnection;

public class DatabaseManager extends DbConnection{
	
	DbConnection connection;
	
	int input = 0;
	
	public DatabaseManager(){}
	
	public DatabaseManager(InputManager handler, Scanner scan){
		
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
		    	  
		    	  Clothing clothing = new Clothing();
		    	 
		    	  int itemNum = rs.getInt("itemNum");
		    	  
		    	  if(itemNum > 1000 && itemNum < 2000) {
		    		  
		    		  clothing = new Shirt();
		    		  
		    	  }else if(itemNum > 2000 && itemNum < 3000 ) {
		    		  
		    		  clothing = new Pants();
		    		  
		    	  }else if (itemNum > 3000) {
		    		  
		    		  clothing = new Hat();
		    		  
		    	  }
		    	  
		    	  clothing.setItemNum(itemNum);
		    	  
		    	  String id  = rs.getString("itemType");
		    	  
		    	  if(itemNum > 1000 && itemNum < 2000) {
		    		  
		    		 String item = itemNum + "";
		    		 
		    		  if(item.charAt(item.length()) == '1') {
		    			  
		    			  ((Shirt) clothing).setSleeveLength("no sleeves");
		    			  
		    		  }else if(item.charAt(item.length()) == '2') {
		    			  
		    			  ((Shirt) clothing).setSleeveLength("short sleeves");
		    			  
		    		  }else if(item.charAt(item.length()) == '3') {
		    			  
		    			  ((Shirt) clothing).setSleeveLength("long sleeves");
		    			  
		    		  }
		    		  
		    	  }else if(itemNum > 2000 && itemNum < 3000 ) {
		    		  
		    		  ((Pants) clothing).setPantLength(id);
		    		  
		    	  }
		    	  
		    	  String size  = rs.getString("itemSize");
		    	  
		    	  clothing.setSize(size);
		    	  
		    	  String color = rs.getString("color");
		    	  
		    	  clothing.setColor(color);
		    	  
		    	  double price = rs.getDouble("price");
		    	  
		    	  clothing.setPrice(price);
		    	  
		    	  clothes.add(clothing);
		    	  
		      }
		     
		      connection.closeConnection();
		      
		      
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		
		return clothes;
		
	}	

}
