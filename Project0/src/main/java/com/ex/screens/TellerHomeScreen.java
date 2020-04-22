package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;

/**
 * TellerHomeScreen: the main menu for the teller after all actions and logging in
 * this screen returns new instances of different screens based on the action taken by the teller
 * 
 * class variables
 * private BankTellerUser user: the teller currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class TellerHomeScreen implements Screen {
	private BankTellerUser user;
	
	
	public TellerHomeScreen(User user) {
		this.user = (BankTellerUser) user;
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
			System.out.println("4. Deposit Funds");
			System.out.println("5. Add New Customer Account");
			System.out.println("6. Remove Customer Account");
			System.out.println("7. Exit Application");
			System.out.print("\nPlease Select Action: ");
			action_selection = scanner.next();
			
			if(action_selection.equals("1.") || action_selection.equals("1") || action_selection.equalsIgnoreCase("v")) {
				System.out.println("\nName: " + user.getFirst_name() + " " + user.getLast_name() + "\nUsername: " + user.getUsername()
									+ "\nAuthorization Code: " + user.getValidationCode() + "\n");
			} else if(action_selection.equals("2.") || action_selection.equals("2") || action_selection.equalsIgnoreCase("u")) {
				return new UpdateTellerScreen(user);
			} else if(action_selection.equals("3.") || action_selection.equals("3") || action_selection.equalsIgnoreCase("c")) {
				return new ChangePasswordScreen(user);
			} else if(action_selection.equals("4.") || action_selection.equals("4") || action_selection.equalsIgnoreCase("d")) {
				return new DepositScreen(user);
			} else if(action_selection.equals("5.") || action_selection.equals("5") || action_selection.equalsIgnoreCase("a")) {
				return new NewCustomerScreen(user);
			} else if(action_selection.equals("6.") || action_selection.equals("6") || action_selection.equalsIgnoreCase("r")) {
				return new RemoveCustomerScreen(user);
			} else if(action_selection.equals("7.") || action_selection.equals("7") || action_selection.equalsIgnoreCase("e")) {
				return null;
			} else {
				System.out.println("Invalid selection. Pick a number from the list or type out the first letter of the action."
						+ "/nExample for Exit Application you could type '7', '7.', 'e' or 'E'.");
			}
		}
	}

}
