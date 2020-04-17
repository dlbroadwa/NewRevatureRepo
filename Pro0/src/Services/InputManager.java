/**
 **Author: Kira Herb
 **
 **Purpose: The purpose of this class is to manage input. This can be changed so that
 **You get a desired output.
 **
 **Date Created: 4/14/20
 **
 **Date last updated: 4/17/20
 **
 **Last Change: Changed the logic so that I can use one method for all input instead of the different methods
 **
 **/

package Services;

import java.util.Scanner;

public class InputManager {
	
	//Default Constructor does nothing but I wanted to include it in case I have a need to add more functionally later
	
	public InputManager(){}
	
	//This method's purpose is to handle input and give me a number between the highest number and the lowest number
	
	public int iputValidateString(String input, Scanner scan, int numMin, int numMax){
		
		int returnNum = 0;
		
		while(returnNum < numMin || returnNum > numMax) {
			
			try {
				
				returnNum = Integer.parseInt(input);
				
				if(returnNum < numMin || returnNum > numMax) {
					
					System.out.println("Error not a valid input: Please select from the menu");
					
					input = scan.nextLine();
					
				}
				
			}catch (NumberFormatException nfe) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
				
		}
		
		
		return returnNum;
		
	}
	
}
