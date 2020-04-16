/**
 **Author: Kira Herb
 **
 **Purpose: The purpose of this class is to manage input. This can be changed so that
 **You get a desired output.
 **
 **Date Created: 4/14/20
 **
 **Date last updated: 4/14/20
 **
 **Last Change: Added the inputValidateColor method, This gives me a number between 1 and 7.
 **
 **/

package Services;

import java.util.Scanner;

public class InputManager {
	
	//Default Constructor does nothing but I wanted to include it in case I have a need to add more functionally later
	
	public InputManager(){}
	
	//This method's purpose is to handle input and give me either 1,2,3,4,5,6, or 7
	
	public int iputValidateColor(String input, Scanner scan){

		
		int i = 0;
		
		//Try to catch the exception just in case the user did not input a value that can be parsed as a int. 
		
		try {
		
		i = Integer.parseInt(input);
		
		}catch(NumberFormatException e) {
			
			//This is in case the user has enter something that can not be parsed as a Int
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")
					&& !input.equals("4") && !input.equals("5") && !input.equals("6")
					&& !input.equals("7")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			//This loop will run until the user has enter 1, 2, 3, 4, 5, 6 or 7 (the numbers I am looking for)
			
			i = Integer.parseInt(input); //Parses the String as a Int
			
		}
		
		if(i < 1 || i > 7) {
			
			//This is just in case the user provides a number that is not what I'm expecting
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")
					&& !input.equals("4") && !input.equals("5") && !input.equals("6")
					&& !input.equals("7")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			/*I don't have to try catch because the way the logic in the loop works
			**You can not exit the loop without being 1,2, 3, 4, 5, 6, or 7 which can all be 
			**Parsed as ints*/
			
			i = Integer.parseInt(input);
			
		}
		
		return i;
		
	}
	
	//This method's purpose is to handle input and give me either 1, 2, 3, 4, or 5
	
	public int iputValidateSize(String input, Scanner scan){

		
		int i = 0;
		
		//Try to catch the exception just in case the user did not input a value that can be parsed as a int. 
		
		try {
		
		i = Integer.parseInt(input);
		
		}catch(NumberFormatException e) {
			
			//This is in case the user has enter something that can not be parsed as a Int
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")
					&& !input.equals("4") && !input.equals("5")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			//This loop will run until the user has enter 1, 2, 3, 4, or 5 (the numbers I am looking for)
			
			i = Integer.parseInt(input); //Parses the String as a Int
			
		}
		
		if(i < 1 || i > 5) {
			
			//This is just in case the user provides a number that is not what I'm expecting
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")
					&& !input.equals("4") && !input.equals("5")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			/*I don't have to try catch because the way the logic in the loop works
			**You can not exit the loop without being 1,2, 3, 4, or 5 which can all be 
			**Parsed as ints*/
			
			i = Integer.parseInt(input);
			
		}
		
		return i;
		
	}
	
	//This method's purpose is to handle input and give me either 1, 2, or 3
	
	public int iputValidate3(String input, Scanner scan){
		
		int i = 0;
		
		//Try to catch the exception just in case the user did not input a value that can be parsed as a int. 
		
		try {
		
		i = Integer.parseInt(input);
		
		}catch(NumberFormatException e) {
			
			//This is in case the user has enter something that can not be parsed as a Int
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			//This loop will run until the user has enter 1, 2, or 3 (the numbers I am looking for)
			
			i = Integer.parseInt(input); //Parses the String as a Int
			
		}
		
		if(i < 1 || i > 3) {
			
			//This is just in case the user provides a number that is not what I'm expecting
			
			while(!input.equals("1") && !input.equals("2") && !input.equals("3")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			/*I don't have to try catch because the way the logic in the loop works
			**You can not exit the loop without being 1,2, or 3 which can all be 
			**Parsed as ints*/
			
			i = Integer.parseInt(input);
			
		}
		
		return i;
		
	}

	//This method's purpose is to handle input and give me either 1 or 2

	public int iputValidate2(String input, Scanner scan){
		
		
		int i = 0;
		
		//Try to catch the exception just in case the user did not input a value that can be parsed as a int. 
		
		try {
		
		i = Integer.parseInt(input);
		
		}catch(NumberFormatException e) {
			
			//This is in case the user has enter something that can not be parsed as a Int
			
			while(!input.equals("1") && !input.equals("2")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			//This loop will run until the user has enter 1,or  2 (the numbers I am looking for)
			
			i = Integer.parseInt(input); //Parses the String as a Int
			
		}
		
		if(i < 1 || i > 2) {
			
			//This is just in case the user provides a number that is not what I'm expecting
			
			while(!input.equals("1") && !input.equals("2")) {
				
				System.out.println("Error not a valid input: Please select from the menu");
				
				input = scan.nextLine();
				
			}
			
			/*I don't have to try catch because the way the logic in the loop works
			**You can not exit the loop without being 1,or 2 which can all be 
			**Parsed as ints*/
			
			i = Integer.parseInt(input);
			
		}
		
		return i;
		
	}
	
}
