package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.ValidationService;

/**
 * UpdateCustomerScreen: this screen invokes the accounts service to update the user information for a bank customer
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class UpdateCustomerScreen implements Screen {
	private User user;
	
	public UpdateCustomerScreen(User user) {
		this.user = user;
	}

	@Override
	public Screen display(Application app) {
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		ValidationService vs = ((OnlineBankApplication)app).getValidationService();
		AccountsService as = ((OnlineBankApplication)app).getAccountsService();
		String username = null;
		String first_name;
		String last_name;
		String address;
		String phone_number;
		boolean valid_username = false;
		
		System.out.print("Enter a new first name or --s to keep the current name (" + user.getFirst_name() +"): ");
		first_name = scanner.next();
		first_name = ((first_name.equals("--s")) ? user.getFirst_name() : first_name);
		System.out.print("Enter a new last name or --s to keep the current name (" + user.getLast_name() +"): ");
		last_name = scanner.next();
		last_name = ((last_name.equals("--s")) ? user.getLast_name() : last_name);
		scanner.nextLine();
		System.out.print("Enter a new address or --s to keep the current address (" + ((BankCustomerUser)user).getAddress() +"): ");
		address = scanner.nextLine();
		address = ((address.equals("--s")) ? ((BankCustomerUser)user).getAddress() : address);
		System.out.print("Enter a new phone number or --s to keep the current phone number (" + ((BankCustomerUser)user).getPhone_number() +"): ");
		phone_number = scanner.nextLine();
		phone_number = ((phone_number.equals("--s")) ? ((BankCustomerUser)user).getPhone_number() : phone_number);
		while(!valid_username) {
			System.out.print("Enter a new username or --s to keep the current username (" + user.getUsername() +"): ");
			username = scanner.nextLine();
			if(username.equals("--s")) {
				username = user.getUsername();
				valid_username = true;
			} else {
				if(vs.validate_username(username) != null) {
					System.out.println("User with that username exists! Select a new username.");
				} else {
					valid_username = true;
				}
			}
		}
		
		System.out.println("Updating account information.");
		User newUser = new BankCustomerUser(first_name,last_name,username,user.getPassword(),'c',address,phone_number,((BankCustomerUser)user).getBalance());
		user = as.updateUserInfo(newUser, user.getUsername());
		System.out.println("Update complete.");
		
		return new CustomerHomeScreen(user);
	}

}
