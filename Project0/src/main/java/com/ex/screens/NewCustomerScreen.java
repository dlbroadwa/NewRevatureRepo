package main.java.com.ex.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.ValidationService;

/**
 * NewCustomerScreen: a class that displays the prompts to generate a new user-defined bank customer
 * it invoke the AccountsService to persist the changes
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class NewCustomerScreen implements Screen {
	private User user;

	public NewCustomerScreen(User user) {
		this.user = user;
	}

	@Override
	public Screen display(Application app) {
		ValidationService vs = ((OnlineBankApplication)app).getValidationService();
		AccountsService as = ((OnlineBankApplication)app).getAccountsService();
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		String first_name;
		String last_name;
		String username = null;
		String password;
		String address;
		String phone_number;
		double balance = 0;
		boolean valid_username = false;
		boolean valid_balance = false;
		
		System.out.print("Enter the customer's first name: ");
		first_name = scanner.next();
		System.out.print("Enter the customer's last name: ");
		last_name = scanner.next();
		while(!valid_username) {
			System.out.print("Enter the customer's username: ");
			username = scanner.next();
			if(vs.validate_username(username) != null) {
				System.out.println("User with that username exists! Select a new username.");
			} else {
				valid_username = true;
			}
		}
		System.out.print("Enter the customer's password: ");
		password = scanner.next();
		scanner.nextLine();
		System.out.print("Enter the customer's address: ");
		address = scanner.nextLine();
		System.out.print("Enter the customer's phone number (no dashes or spaces): ");
		phone_number = scanner.nextLine();
		while(!valid_balance) {
			System.out.print("Enter the customer's starting balance: $");
			try {
				balance = scanner.nextDouble();
			} catch(InputMismatchException e) {
				scanner.next();
			}
			if(balance <= 0) {
				System.out.println("Invalid amount. Please enter an amount larger than 0.00");
			} else {
				valid_balance = true;
			}
		}
		
		System.out.println("Creating the new customer's account.");
		User newUser = new BankCustomerUser(first_name,last_name,username,password,'c',address,phone_number,balance);
		as.createNewUser(newUser);
		System.out.println("New account created for " + newUser.getFirst_name() + " " + newUser.getLast_name() + ".");
		
		return new TellerHomeScreen(user);
	}
}
