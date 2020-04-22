package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.User;
import main.java.com.services.AccountsService;
import main.java.com.services.ValidationService;

/**
 * ChangePasswordScreen: a class that displays the prompts to change a user's password
 * it invokes the accounts service to persist the changes
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class ChangePasswordScreen implements Screen{
	private User user;
	
	public ChangePasswordScreen(User user) {
		this.user = user;
	}

	@Override
	public Screen display(Application app) {
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		ValidationService vs = ((OnlineBankApplication)app).getValidationService();
		AccountsService as = ((OnlineBankApplication)app).getAccountsService();
		String currPassword;
		String newPassword0 = null;
		String newPassword1;
		boolean valid_password = false;
		boolean matching_password = false;
		
		while(!valid_password) {
			System.out.print("Enter your current password: ");
			currPassword = scanner.next();
			if(vs.validate_username_password(user.getUsername(),currPassword) == null) {
				System.out.println("Incorrect Password");
			}else {
				valid_password = true;
			}
		}
		while(!matching_password) {
			System.out.print("Enter your new password: ");
			newPassword0 = scanner.next();
			System.out.print("Re-enter your new password(it must match the one above): ");
			newPassword1 = scanner.next();
			if(!newPassword0.equals(newPassword1)) {
				System.out.println("The passwords do not match. Try again.");
			} else {
				matching_password = true;
			}
		}
		System.out.println("Updating password.");
		as.updatePassword(user, newPassword0);
		System.out.println("Password Updated.");
		
		if(user.getAccess_level() == 'c') {
			return new CustomerHomeScreen(user);
		}
		return new TellerHomeScreen(user);
	}
}
