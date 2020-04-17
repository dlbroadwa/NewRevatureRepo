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
 **Date last updated: 4/16/20
 **
 **Last Change: added the logic for the itemNum 
 **
 **/

import java.util.Scanner;

import Services.DatabaseManager;
import Services.InputManager;
import util.DbConnection;

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
		
		int input = handler.iputValidateString(scan.nextLine() , scan, 1, 2);

		if(input == 1) {
			
			DatabaseManager con = new DatabaseManager(scan, handler); 
			
			System.out.println("Please choose a clothing item from the list:");
			
			System.out.println("	1 - Shirt");
			
			System.out.println("	2 - Pants");
			
			System.out.println("	3 - Hat");
			
			input = handler.iputValidateString(scan.nextLine() , scan, 1 , 3);
			
			switch(input) {
			
			case 1: con.searchFor("itemNum > 1000 AND itemNum < 2000");
					break;
			case 2: con.searchFor("itemNum > 2000 AND itemNum < 3000");
					break;
			case 3: con.searchFor("itemNum > 3000 AND itemNum < 4000");
					break;
			}
			
		}
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
			
			input = handler.iputValidateString(scan.nextLine() , scan, 1, 3);
			
			if(input == 1) {
				
				clothing = new Shirt();
				
				total += 3;
				
				clothing.setPrice (clothing.getPrice() + 3);
				
				System.out.println("Please choose size:");
				
				System.out.println("	1 - xs");
				
				System.out.println("	2 - s");
				
				System.out.println("	3 - m");
				
				System.out.println("	4 - l");
				
				System.out.println("	5 - xl");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 5);
				
				switch(input) {
				
					case 1: clothing.setSize("xs");
							clothing.setItemNum(1100);
							break;
						
					case 2: clothing.setSize("s");
							clothing.setItemNum(1200);
							break;
						
					case 3: clothing.setSize("m");
							clothing.setItemNum(1300);
							break;
						
					case 4: clothing.setSize("l");
							clothing.setItemNum(1400);
							break;
						
					case 5: clothing.setSize("xl");
							clothing.setItemNum(1500);
							clothing.setPrice(clothing.getPrice() + 1);
							total += 1;
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
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 7);
				
				switch(input) {
				
					case 1: clothing.setColor("black");
							clothing.setItemNum(clothing.getItemNum() + 10);
							break;
					
					case 2: clothing.setColor("white");
							clothing.setItemNum(clothing.getItemNum() + 20);
							break;
					
					case 3: clothing.setColor("blue");
							clothing.setItemNum(clothing.getItemNum() + 30);
							break;
					
					case 4: clothing.setColor("red");
							clothing.setItemNum(clothing.getItemNum() + 40);
							break;
					
					case 5: clothing.setColor("green");
							clothing.setItemNum(clothing.getItemNum() + 50);
							break;
						
					case 6: clothing.setColor("orange");
							clothing.setItemNum(clothing.getItemNum() + 60);
							break;	
						
					case 7: clothing.setColor("purple");
							clothing.setItemNum(clothing.getItemNum() + 70);
							break;
			
				}
				
				System.out.println("Please select your sleeve size:");
				
				System.out.println("	1 - no sleeves");
				
				System.out.println("	2 - short sleeves");
				
				System.out.println("	3 - long sleeves");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 3);
				
				switch(input) {
					
					case 1: ((Shirt) clothing).setSleeveLength("no sleeves");
							clothing.setItemNum(clothing.getItemNum() + 1);
							break;
					
					case 2:((Shirt) clothing).setSleeveLength("short sleeves");
							clothing.setItemNum(clothing.getItemNum() + 2);
							break;
						
					case 3: ((Shirt) clothing).setSleeveLength("long sleeves");
							clothing.setItemNum(clothing.getItemNum() + 1);
							clothing.setPrice(clothing.getPrice() + 1);			
							total += 1;
							break;
					
				}
				
			}
			
			else if(input == 2) {
				
				clothing = new Pants();
				
				total += 4;
				
				clothing.setPrice (clothing.getPrice() + 4);
				
				System.out.println("Please choose size:");
				
				System.out.println("	1 - xs");
				
				System.out.println("	2 - s");
				
				System.out.println("	3 - m");
				
				System.out.println("	4 - l");
				
				System.out.println("	5 - xl");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 5);
				
				switch(input) {
				
					case 1: clothing.setSize("xs");
							clothing.setItemNum(2100);
							break;
						
					case 2: clothing.setSize("s");
							clothing.setItemNum(2200);
							break;
						
					case 3: clothing.setSize("m");
							clothing.setItemNum(2300);
							break;
						
					case 4: clothing.setSize("l");
							clothing.setItemNum(2400);
							break;
						
					case 5: clothing.setSize("xl");
							clothing.setItemNum(2500);
							clothing.setPrice(clothing.getPrice() + 1);
							total += 1;
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
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 7);
				
				switch(input) {
				
					case 1: clothing.setColor("black");
							clothing.setItemNum(clothing.getItemNum() + 10);
							break;
					
					case 2: clothing.setColor("white");
							clothing.setItemNum(clothing.getItemNum() + 20);
							break;
					
					case 3: clothing.setColor("blue");
							clothing.setItemNum(clothing.getItemNum() + 30);
							break;
					
					case 4: clothing.setColor("red");
							clothing.setItemNum(clothing.getItemNum() + 40);
							break;
					
					case 5: clothing.setColor("green");
							clothing.setItemNum(clothing.getItemNum() + 50);
							break;
						
					case 6: clothing.setColor("orange");
							clothing.setItemNum(clothing.getItemNum() + 60);
							break;	
						
					case 7: clothing.setColor("purple");
							clothing.setItemNum(clothing.getItemNum() + 70);
							break;
			
				}
				
				System.out.println("Please select your pants length:");
				
				System.out.println("	1 - shorts");
				
				System.out.println("	2 - pants");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 2);
				
				switch(input) {
					
					case 1: ((Pants) clothing).setPantLength("shorts");
							clothing.setItemNum(clothing.getItemNum() + 1);
							break;
						
					case 2: ((Pants) clothing).setPantLength("pants");
							clothing.setItemNum(clothing.getItemNum() + 2);
							clothing.setPrice(clothing.getPrice() + 1);
							total += 1;
							break;
							
				}
				
			}
			else {
				
				clothing = new Hat();
				
				clothing.setPrice (clothing.getPrice() + 2);
				
				System.out.println("Please choose size:");
				
				System.out.println("	1 - xs");
				
				System.out.println("	2 - s");
				
				System.out.println("	3 - m");
				
				System.out.println("	4 - l");
				
				System.out.println("	5 - xl");
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 5);
				
				switch(input) {
				
					case 1: clothing.setSize("xs");
							clothing.setItemNum(3100);
							break;
						
					case 2: clothing.setSize("s");
							clothing.setItemNum(3200);
							break;
						
					case 3: clothing.setSize("m");
							clothing.setItemNum(3300);
							break;
						
					case 4: clothing.setSize("l");
							clothing.setItemNum(3400);
							break;
						
					case 5: clothing.setSize("xl");
							clothing.setItemNum(3500);
							clothing.setPrice(clothing.getPrice() + 1);
							total += 1;
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
				
				input = handler.iputValidateString(scan.nextLine() , scan, 1, 7);
				
				switch(input) {
				
					case 1: clothing.setColor("black");
							clothing.setItemNum(clothing.getItemNum() + 10);
							break;
					
					case 2: clothing.setColor("white");
							clothing.setItemNum(clothing.getItemNum() + 20);
							break;
					
					case 3: clothing.setColor("blue");
							clothing.setItemNum(clothing.getItemNum() + 30);
							break;
					
					case 4: clothing.setColor("red");
							clothing.setItemNum(clothing.getItemNum() + 40);
							break;
					
					case 5: clothing.setColor("green");
							clothing.setItemNum(clothing.getItemNum() + 50);
							break;
						
					case 6: clothing.setColor("orange");
							clothing.setItemNum(clothing.getItemNum() + 60);
							break;	
						
					case 7: clothing.setColor("purple");
							clothing.setItemNum(clothing.getItemNum() + 70);
							break;
			
				}
				
			}
			
			System.out.println("Would you like to order today?");
			
			System.out.println("	1 - Yes");
			
			System.out.println("	2 - No create it and hold it for me please");
			
			input = handler.iputValidateString(scan.nextLine() , scan, 1, 2);
			
			if(input == 1) {}
			else {
				
				System.out.println("Thank you for using the POS system!");
				
				System.out.println("Your " + clothing.toString() + " will be ready soon!");
				
				System.out.println("Your item will be added to the inventory when ready! Have a great day");
				
			}
			
		}
		
	}

}
