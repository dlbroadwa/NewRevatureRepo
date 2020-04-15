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
 **Date last updated: 4/14/20
 **
 **Last Change: Added input manager so the input could be handled. 
 **
 **/

import java.util.Scanner;

import Services.InputManager;

public class Main {

	public static void main(String[] args) {
		
		double total = 0;
		
		System.out.println("Hello and welcome to the Able Sisters POS system!");
		
		/* 
		 *Here I instantiate a instance of the InputManager class
		 *So I can reference and use the class to get my expected
		 *Output.
		 * 
		 */
		
		InputManager handler= new InputManager();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("What would you like to do today:");
		
		System.out.println("	1 - Check inventory");
		
		System.out.println("	2 - Create a new item");
		
		int input = handler.iputValidate2(scan.nextLine() , scan);

		if(input == 1) {}
		else {
			
			System.out.println("Creating new clothing item.......");
			
			/*
			 *Here I am creating a new clothing object as all the other
			 *Clothing items will extend so that it will become
			 *More defined.
			 * 
			 */
			
			Clothing clothing = new Clothing();
			
			System.out.println("Please choose a clothing item from the list:");
			
			System.out.println("	1 - Shirt");
			
			System.out.println("	2 - Pants");
			
			System.out.println("	3 - Hat");
			
			input = handler.iputValidate3(scan.nextLine() , scan);
			
			if(input == 1) {
				
				clothing.setPrice (3);
				
				total += 3;
				
				clothing = new Shirt();
				
				System.out.println("Please choose size:");
				
				System.out.println("	1 - xs");
				
				System.out.println("	2 - s");
				
				System.out.println("	3 - m");
				
				System.out.println("	4 - l");
				
				System.out.println("	5 - xl");
				
				input = handler.iputValidateSize(scan.nextLine() , scan);
				
				switch(input) {
				
					case 1: clothing.setSize("xs");
							break;
						
					case 2: clothing.setSize("s");
							break;
						
					case 3: clothing.setSize("m");
							break;
						
					case 4: clothing.setSize("l");
							break;
						
					case 5: clothing.setSize("xl");
							clothing.setPrice(clothing.getPrice() +.25);
							total += .25;
							break;
				
				}
				
				System.out.println("Please choose a color:");
				
				System.out.println("	1 - black");
				
				System.out.println("	2 - white");
				
				System.out.println("	3 - blue");
				
				System.out.println("	4 - red");
				
				System.out.println("	5 - green");
				
				System.out.println("	6 - orange");
				
				System.out.println("	7 - purple");
				
				input = handler.iputValidateColor(scan.nextLine() , scan);
				
				switch(input) {
				
					case 1: clothing.setColor("black");
							break;
					
					case 2: clothing.setColor("white");
							break;
					
					case 3: clothing.setColor("blue");
							break;
					
					case 4: clothing.setColor("red");
							break;
					
					case 5: clothing.setColor("green");
							break;
						
					case 6: clothing.setColor("orange");
							break;	
						
					case 7: clothing.setColor("purple");
							break;
			
				}
				
				System.out.println("Please select your sleeve size:");
				
				System.out.println("	1 - no sleeves");
				
				System.out.println("	2 - short sleeves");
				
				System.out.println("	3 - long sleeves");
				
				input = handler.iputValidate3(scan.nextLine() , scan);
				
				switch(input) {
					
					case 1: ((Shirt) clothing).setSleeveLength("no sleeves");
							break;
					
					case 2:((Shirt) clothing).setSleeveLength("short sleeves");
							break;
						
					case 3: ((Shirt) clothing).setSleeveLength("long sleeves");
							clothing.setPrice(clothing.getPrice() +.25);
							total += .25;
							break;
					
				}
				
			}
			
			else if(input == 2) {
				
				clothing = new Pants();
				
				System.out.println("Please choose size:");
				
				System.out.println("	1 - xs");
				
				System.out.println("	2 - s");
				
				System.out.println("	3 - m");
				
				System.out.println("	4 - l");
				
				System.out.println("	5 - xl");
				
				input = handler.iputValidateSize(scan.nextLine() , scan);
				
				switch(input) {
				
					case 1: clothing.setSize("xs");
							break;
						
					case 2: clothing.setSize("s");
							break;
						
					case 3: clothing.setSize("m");
							break;
						
					case 4: clothing.setSize("l");
							break;
						
					case 5: clothing.setSize("xl");
							clothing.setPrice(clothing.getPrice() +.25);
							total += .25;
							break;
				
				}
				
				System.out.println("Please choose a color:");
				
				System.out.println("	1 - black");
				
				System.out.println("	2 - white");
				
				System.out.println("	3 - blue");
				
				System.out.println("	4 - red");
				
				System.out.println("	5 - green");
				
				System.out.println("	6 - orange");
				
				System.out.println("	7 - purple");
				
				input = handler.iputValidateColor(scan.nextLine() , scan);
				
				switch(input) {
				
					case 1: clothing.setColor("black");
							break;
					
					case 2: clothing.setColor("white");
							break;
					
					case 3: clothing.setColor("blue");
							break;
					
					case 4: clothing.setColor("red");
							break;
					
					case 5: clothing.setColor("green");
							break;
						
					case 6: clothing.setColor("orange");
							break;	
						
					case 7: clothing.setColor("purple");
							break;
			
				}
				
				System.out.println("Please select your pants length:");
				
				System.out.println("	1 - shorts");
				
				System.out.println("	2 - knee-length");
				
				input = handler.iputValidate2(scan.nextLine() , scan);
				
				switch(input) {
					
					case 1: ((Pants) clothing).setPantLength("shorts");
							break;
						
					case 2: ((Pants) clothing).setPantLength("pants");
							clothing.setPrice(clothing.getPrice() +.25);
							total += .25;
							break;
							
				}
				
			}
			else {}
			
			System.out.println("Would you like to order today?");
			
			System.out.println("	1 - Yes");
			
			System.out.println("	2 - No create it and hold it for me please");
			
			input = handler.iputValidate2(scan.nextLine() , scan);
			
			if(input == 1) {}
			else {
				
				System.out.println("Thank you for using the POS system!");
				
				System.out.println("Your " + clothing.toString() + " will be ready soon!");
				
				System.out.println("Your item will be added to the inventory when ready! Have a great day");
				
			}
			
		}
		
	}

}
