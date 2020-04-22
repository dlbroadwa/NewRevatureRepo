

package services;

import java.util.ArrayList;
import java.util.Scanner;

import classes.Clothing;

public class OrderService {
	
	String address;
	
	public OrderService(){}
	
	public OrderService(Double total, ArrayList<Clothing> cart, Scanner scan){
		
		System.out.println("Items:");
		
		for(int i = 0; i < cart.size(); i++) {
			
			System.out.println("Item Number: " + cart.get(i).getItemNum() + " " 
			+ cart.get(i) + " " + cart.get(i).getPrice());
			
		}
		
		System.out.println("Sales Tax: " + total * .06);
		
		total += total * .06;
		
		System.out.println("Your total will be: " + total);
		
		System.out.println("Please input a first name for the order:");
		
		String fName = scan.nextLine();
		
		System.out.println("Please input a last name for the order:");
		
		String lName = scan.nextLine();
		
		System.out.println("Please input your address:");
		
		String address = scan.nextLine();
		
		System.out.println("Please input your card number:");
		
		String payment = scan.nextLine();
		
	}
	
}
