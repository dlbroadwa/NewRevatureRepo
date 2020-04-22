package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.ValidationService;

/**
 * RemoveCustomerScreen: a class that displays the prompts to remove a user-specified customer from the data repository
 * it invokes the AccountsService to persist the changes
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class RemoveCustomerScreen implements Screen {
	private User user;
	
	public RemoveCustomerScreen(User user) {
		this.user = user;
	}

	@Override
	public Screen display(Application app) {
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		ValidationService vs = ((OnlineBankApplication)app).getValidationService();
		AccountsService as = ((OnlineBankApplication)app).getAccountsService();
		String username = null;
		boolean valid_username = false;
		
		while(!valid_username) {
			System.out.print("Enter the username of the customer account being removed: ");
			username = scanner.next();
			if(vs.validate_username(username) == null) {
				System.out.println("Invalid Username");
			} else {
				valid_username = true;
			}
		}
		System.out.println("Removing " + username + ".");
		as.deleteUser(username);
		System.out.println(username + " removed.");
		return new TellerHomeScreen(user);
	}

}
