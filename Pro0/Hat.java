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

public class Hat extends Clothing {
	
	Hat(){
		
		System.out.println("Create new hat object....");
		
	}
	
	public String toString() {
		
		
		return getColor() + " " + getSize() + " " + "hat";
		
	}

}
