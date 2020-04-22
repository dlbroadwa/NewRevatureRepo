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
 **Last Change: Changed the organization so that another object will handle the
 **printing statements and I don't need to pass the scanner object around within
 **reason.
 **
 **/

package classes;

public class Shirt extends Clothing {
	
	//The only field that every other class does not have
	
	private String sleeveLength;
	
	public Shirt(){
		
		//This is Shirt's constructor informing the user that a new shirt object is being created
		
		System.out.println("Creating new shirt object....");

	}//end of Shirt()

	public String getSleeveLength() {
		
		//This will return the current sleeveLength
		
		return sleeveLength;
		
	}//ending of getSleeveLength()	

	public void setSleeveLength(String sleeveLength) {
		
		//This will set the current sleeveLength
		
		this.sleeveLength = sleeveLength;
		
	}//ending of setSleeveLength()
	
	public String toString() {
		
		/*This is overrideing the object toString method. When a shirt object is called from a 
		 * System.out.print statement or shirt.toString() this is the method that will be called
		 */
		
		return getSize() + " " + getColor() + " " + sleeveLength + " " + "shirt";
	
	}//ending of toString()

}//ending of Shirt