/**
 ** Author: Kira Herb
 **
 **Purpose: The purpose of the Able Sister's POS system is to allow 
 **customers to check the inventory of the clothing store, create a new 
 **clothing item and place a order for clothes
 **
 **Date created: 4/7/20
 **
 **Current Version: 1.2
 **
 **Date last updated: 4/20/20
 **
 **Last Change: changed the logic so that multple items can be ordered and added to the cart.
 **
 **/

package classes;
import java.util.ArrayList;
import java.util.Scanner;
import services.*;
import util.*;

public class MainClass {

	public static void main(String[] args) {

		//This is were the program will start to run
		
		//Below are my flields
		
		InputManager handler = new InputManager();
		
		boolean addingMore = true; //Will change if the customer decides to not add another item
		
		double total = 0; // keeps track of how much the customer owes
		
		int input; //will be used to determine the next action
		
		// created this class for handling input and this will allow me to use it
		
		ArrayList<Clothing> cart = new ArrayList<Clothing>(); //This will be used as the customers "cart"
		
		Scanner scan = new Scanner(System.in); //Regular scanner object
		
		/*
		 * simple print out statements that will allow the user to determine what action to take
		 * 
		 */
		
		System.out.println("Hello and welcome to the Able Sisters POS system!");
		
		System.out.println("What would you like to do today:");
		
		System.out.println("	1 - Check inventory");
		
		System.out.println("	2 - Create a new item");
		
		input = handler.iputValidateString(scan.nextLine() , scan, 1, 2);
		
		//This if statement only needs to handle if the input is 1 or 2 due to the nature of the InputHandler class
		if(input == 1) {
			
			DatabaseManager db = new DatabaseManager(handler, scan);
			
			
			
		}//end of if(input == 1)
		else if(input == 2) {
			
			/*
			 * CreatorService is the class that will handle the creation of the clothing objects.
			 * Here I am calling the constructor so I can start creating objects
			 * 
			 */
			
			CreatorService creator = new CreatorService(handler, scan); 
			
			while(addingMore) {
				
				//While addingMore is true this while statement will run
				
				Clothing clothing = creator.create(); //The create method is the method that handles the creation of clothing objects
				
				total += clothing.getPrice(); //This gets the price of the object and adds it to the total
				
				cart.add(clothing); //This adds the newly created object to the cart
				
				/*
				 * simple print out statements that will allow the user to determine what action to take
				 * 
				 */
				
				System.out.println("Would you like to add another item to your cart?");
				
				System.out.println("	1 - Yes");
				
				System.out.println("	2 - No");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 2);
				
				//Since addingMore is already true I only need to handle if the customer wants to stop
				
				if(input == 2) {
					
					addingMore = false; //Updating addingMore to false so that the while loop can end
					
				} //end of if(input == 2)
				
			}//end of while(addingMore)
			
		}//end of else if(input == 2) {

		OrderService order = new OrderService(total, cart, scan); //This will start the orderService so the customer can pay
		
		/*
		 * simple system.out.print statements to thank the user and let them know their order is being worked on
		 *
		 */
		
		System.out.println("Thank you for using the Able Sisters POS system!");
		
		System.out.println("Your items will be ready soon!");
		
	} //end of main(String[] args)

}//end of MainClass
