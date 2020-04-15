/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of this class is to be extend the clothing classes
 **And making it more defined by adding a length for the pant legs something
 **That only pants have.
 **
 **Date created: 4/7/20
 **
 **Date last updated: 4/14/20
 **
 **Last Change: Changed the organization so that the main method will handle the
 **printing statements and I don't need to pass the scanner object around within
 **reason.
 **
 **/

public class Pants extends Clothing {

	private String pantLength;
	
	Pants(){
		
		System.out.println("...creating new pants object");
		
	}
	
	public String toString(){
		
		return getSize() + " " + getColor() + " " + pantLength;
		
	}

	public void setPantLength(String string) {
		
		pantLength = string;
		
	}
	
	public String getPantLength() {
		
		return pantLength;
		
	}
	
}
