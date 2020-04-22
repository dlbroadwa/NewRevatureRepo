/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of this class is to be extended by more clothing classes
 **Each piece of clothing has a size, color, and price. 
 **
 **Date created: 4/9/20
 **
 **Date last updated: 4/16/20
 **
 **Last Change: Added the getter and setters for itemNum
 **
 **/

package classes;

public class Clothing {

	// These are my fields every piece of clothing has
	
	private String size;
	
	private String color;
	
	private double price;
	
	private int itemNum;
	
	//Has a invisible Clothing() constructor

	public double getPrice() {
		
		//This will return the price
		
		return price;
	
	}//end of getPrice()

	public void setPrice(double price) {
		
		//This will set the price
		
		this.price = price;
	
	}//end of setPrice()

	public String getColor() {
		
		//This will return the current color
		
		return color;
	
	}//end of getColor()

	public void setColor(String color) {
		
		//This will set the color
		
		this.color = color;
	
	}//end of setColor()

	public String getSize() {
		
		//This will return the current size
	
		return size;
	
	}//end of getSize()

	public void setSize(String size) {
	
		//This will set the current size
		
		this.size = size;
	
	}//end of setSize()

	public int getItemNum() {
		
		//This will return the current ItemNum
		
		return itemNum;
	
	}//end of getItemNum()

	public void setItemNum(int itemNum) {
		
		//This will set the current ItemNum
		
		this.itemNum = itemNum;
	
	}//end of setItemNum()
	
}//end of Clothing
