/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of this class is to be extended by more clothing classes
 **Each piece of clothing has a size, color, and price. 
 **
 **Date created: 4/9/20
 **
 **Date last updated: 4/9/20
 **
 **Last Change: Added the getter and setters 
 **
 **/

public class Clothing {

	private String size;
	
	private String color;
	
	private double price;

	public double getPrice() {
		
		return price;
	
	}

	public void setPrice(double price) {
		
		this.price = price;
	
	}

	public String getColor() {
		
		return color;
	
	}

	public void setColor(String color) {
		
		this.color = color;
	
	}

	public String getSize() {
	
		return size;
	
	}

	public void setSize(String size) {
	
		this.size = size;
	
	}
	
}
