/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of this class is to be extend the clothing classes
 **And making it more defined by adding a length for the sleeves something
 **That only shirts have.
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

public class Shirt extends Clothing {
	
	private String sleeveLength;
	
	Shirt(){
		
		System.out.println("Creating new shirt object....");

	}

	public String getSleeveLength() {
		
		return sleeveLength;
		
	}

	public void setSleeveLength(String sleeveLength) {
		
		this.sleeveLength = sleeveLength;
		
	}
	
	public String toString() {
		
		return getSize() + " " + getColor() + " " + sleeveLength + " " + "shirt";
	}

}
