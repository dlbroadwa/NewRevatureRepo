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

package services;

import java.util.Scanner;

public class InputManager {
	
	//Invisible Constructor InputManager()
	
	//This method's purpose is to handle input and give me a number between the highest number and the lowest number
	
	public int iputValidateString(String input, Scanner scan, int numMin, int numMax){
		
		int returnNum = 0; //This will be the item I am returning
		
		while(returnNum < numMin || returnNum > numMax) {
			
			//This loop will continue to run until returnNum is between highest num and lower number
			
			try {
				
				returnNum = Integer.parseInt(input); //This attempts to parse the input to a integer 
				
				if(returnNum < numMin || returnNum > numMax) {
					
					//This input handles if the input could be Parsed but is not a value between the highest and lowest numbers
					
					System.out.println("Error not a valid input: Please select from the menu");//lets the user know its not a valid input
					
					input = scan.nextLine();
					
				}//end of if(returnNum < numMin || returnNum > numMax)
				
			}//end of try{}
			
			catch (NumberFormatException nfe) {
				
				 //This will catch if the input can not be parsed to a Int
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}//end of catch (NumberFormatException nfe)
				
		}//end of while(returnNum < numMin || returnNum > numMax)
		
		return returnNum; //returns returnNum
		
	}//end of iputValidateString(String input, Scanner scan, int numMin, int numMax)
	
}//end of InputManager
