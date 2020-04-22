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
 **Last Change: Changed the organization so that another object will handle the
 **printing statements and I don't need to pass the scanner object around within
 **reason.
 **
 **/

package classes;

public class Pants extends Clothing {

	//The only field that every other piece of clothing does not have
	
	private String pantLength;
	
	public Pants(){
		
		//This is Pants's constructor informing the user that a new pants object is being created
		
		System.out.println("Creating new pants object....");
		
	}//end of Pants()
	
	public String toString(){
		
		/*This is overriding the object toString method. When a pant object is called from a 
		 * System.out.print statement or pant.toString() this is the method that will be called
		 */
		
		return getSize() + " " + getColor() + " " + pantLength;
		
	}//end of toString()

	public void setPantLength(String string) {
		
		//This is setting the current pantLength
		
		pantLength = string;
		
	}//end of setPantLength()
	
	public String getPantLength() {
		
		//This will return the current pantLength
		
		return pantLength;
		
	}//end of getPantLength()
	
}//end of Pants
