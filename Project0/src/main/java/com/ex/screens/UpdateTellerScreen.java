package main.java.com.ex.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.ValidationService;

/**
 * UpdateTellerScreen: a class that displays the prompts to update the user information for a teller
 * it invokes the accounts service to persist the changes
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class UpdateTellerScreen implements Screen {
	private User user;
	
	public UpdateTellerScreen(User user) {
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
		int auth_code = 0;
		boolean valid_username = false;
		boolean valid_auth_code = false;
		
		System.out.print("Enter a new first name or --s to keep the current name (" + user.getFirst_name() +"): ");
		first_name = scanner.next();
		first_name = ((first_name.equals("--s")) ? user.getFirst_name() : first_name);
		System.out.print("Enter a new last name or --s to keep the current name (" + user.getLast_name() +"): ");
		last_name = scanner.next();
		last_name = ((last_name.equals("--s")) ? user.getLast_name() : last_name);
		while(!valid_username) {
			System.out.print("Enter a new username or --s to keep the current username (" + user.getUsername() +"): ");
			username = scanner.next();
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
		while(!valid_auth_code) {
			System.out.print("Enter a new authorization code or -1 to keep the current code (" + ((BankTellerUser)user).getValidationCode() + "): ");
			try {
				auth_code = scanner.nextInt();
			} catch(InputMismatchException e) {
				scanner.next();
			}
			if(auth_code == -1) {
				auth_code = ((BankTellerUser)user).getValidationCode();
				valid_auth_code = true;
			} else if(auth_code < 100000 || auth_code > 99999) {
				System.out.println("Invalid Authorization Code. Code must be 6 digits long.");
			}  else {
				valid_auth_code = true;
			}
		}
		
		System.out.println("Updating information.");
		User newUser = new BankTellerUser(first_name,last_name,username,user.getPassword(),'t',auth_code);
		user = as.updateUserInfo(newUser, user.getUsername());
		System.out.println("Update complete.");
		
		return new TellerHomeScreen(user);
	}

}
