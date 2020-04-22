/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of this class is extend the clothing classes
 **And making it more defined
 **
 **Date created: 4/9/20
 **
 **Date last updated: 4/9/20
 **
 **Last Change: Added the to String class
 **
 **/

package classes;

public class Hat extends Clothing {
	
	public Hat(){
		
		//This is hat's constructor informing the user that a new hat object is being created
		
		System.out.println("Create new hat object....");
		
	} //end of Hat()
	
	public String toString() {
		
		/*This is overrideing the object toString method. When a hat object is called from a 
		 * System.out.print statement or hat.toString() this is the method that will be called
		 */
		
		return getSize() + " " + getColor() + " " + "hat";
		
	} //end of toString()

} //end of Hat
