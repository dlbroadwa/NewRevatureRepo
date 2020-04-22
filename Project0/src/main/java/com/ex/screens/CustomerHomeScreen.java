package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.User;

/**
 * CustomerHomeScreen: the main menu for the customer after all actions and logging in
 * this screen returns new instances of different screens based on the action taken by the customer
 * 
 * class variables
 * private BankCustomerUser user: the customer currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class CustomerHomeScreen implements Screen {
	private BankCustomerUser user;
	
	public CustomerHomeScreen(User user) {
		this.user = (BankCustomerUser) user;
	}

	@Override
	public Screen display(Application app) {
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		String action_selection;
		
		while(true) {
			System.out.println("\nWelcome " + user.getFirst_name() +" "+ user.getLast_name());
			System.out.println("1. View Account Information");
			System.out.println("2. Update Account Information");
			System.out.println("3. Change Password");
			System.out.println("4. Withdraw Funds");
			System.out.println("5. Exit Application");
			System.out.print("\nPlease Select Action: ");
			action_selection = scanner.next();
			
			if(action_selection.equals("1.") || action_selection.equals("1") || action_selection.equalsIgnoreCase("v")) {
				System.out.println("\nName: " + user.getFirst_name() + " " + user.getLast_name() +
									"\nUsername: " + user.getUsername() + "\nAddress: " + user.getAddress() +
									"\nPhone Number: " + user.getPhone_number().substring(0, 3) +"-"+ user.getPhone_number().substring(3, 6) +"-"+
									user.getPhone_number().substring(6) + "\nAccount Balance: $" + user.getBalance() + "\n");
			} else if(action_selection.equals("2.") || action_selection.equals("2") || action_selection.equalsIgnoreCase("u")) {
				return new UpdateCustomerScreen(user);
			} else if(action_selection.equals("3.") || action_selection.equals("3") || action_selection.equalsIgnoreCase("c")) {
				return new ChangePasswordScreen(user);
			} else if(action_selection.equals("4.") || action_selection.equals("4") || action_selection.equalsIgnoreCase("w")) {
				return new WithdrawScreen(user);
			} else if(action_selection.equals("5.") || action_selection.equals("5") || action_selection.equalsIgnoreCase("e")) {
				return null;
			} else {//response for invalid selection
				System.out.println("Invalid selection. Pick a number from the list or type out the first letter of the action."
						+ "/nExample for Exit Application you could type '5', '5.', 'e' or 'E'.");
			}
		}

	}

}
