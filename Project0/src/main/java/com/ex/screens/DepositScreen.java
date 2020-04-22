package main.java.com.ex.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.BankTellerUser;
import main.java.com.ex.users.User;
import main.java.com.services.TransactionService;
import main.java.com.services.ValidationService;

/**
 * DepositScreen: a class the displays the prompts to create a user-defined deposit
 * it invokes the TransactionService to persist the changes
 * 
 * class variables
 * private BankTellerUser user: the teller currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class DepositScreen implements Screen {
	BankTellerUser user;
	
	public DepositScreen(User user) {
		this.user = (BankTellerUser) user;
	}

	@Override
	public Screen display(Application app) {
		ValidationService vs = ((OnlineBankApplication)app).getValidationService();
		TransactionService ts = ((OnlineBankApplication)app).getTransactionService();
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		String username;
		double amount = 0;
		boolean valid_amount = false;
		boolean valid_username = false;
		boolean valid_code = false;
		int auth_code = 0;
		User newUser = null;
		
		while(!valid_username) {
			System.out.print("Please enter the customer's username: ");
			username = scanner.next();
			newUser = vs.validate_username(username);
			if(newUser == null) {
				System.out.println("Invalid customer username.");
			} else {
				valid_username = true;
			}
		}
		while(!valid_amount) {
			System.out.print("Please enter the customer's deposit amount: $");
			try {
				amount = scanner.nextDouble();
			}catch(InputMismatchException e) {
				scanner.next();
			}
			if(amount <= 0) {
				System.out.println("Invalid amount. Please enter amount greater than 0.00");
			} else {
				valid_amount = true;
			}
		}
		while(!valid_code) {
			System.out.print("Please enter your authorization code: ");
			try {
				auth_code = scanner.nextInt();
			} catch(InputMismatchException e) {
				scanner.next();
			}
			if(auth_code != user.getValidationCode()) {
				System.out.println("Invalid Authorization Code");
			} else {
				valid_code = true;
			}
		}
		System.out.println("Depositing $" + amount + " into " + newUser.getFirst_name() +" "+ newUser.getLast_name() +"'s account.");
		newUser = ts.transact('d', amount, newUser);
		System.out.println("Deposit Complete\nNew Balance: $" + ((BankCustomerUser)newUser).getBalance());
		
		return new TellerHomeScreen(user);
	}

}
