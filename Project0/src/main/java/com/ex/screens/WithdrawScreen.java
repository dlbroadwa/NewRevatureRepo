package main.java.com.ex.screens;

import java.util.InputMismatchException;
import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.BankCustomerUser;
import main.java.com.ex.users.User;
import main.java.com.services.TransactionService;

/**
 * WithdrawScreen: a class that displays the prompts used to create a user-defined withdraw
 * it invokes the TransactionService to persist the changes
 * 
 * class variables
 * private User user: the user currently logged into the application
 * 
 * @author jtb12_000
 *
 */

public class WithdrawScreen implements Screen {
	private User user;
	
	public WithdrawScreen(User user) {
		this.user = user;
	}

	@Override
	public Screen display(Application app) {
		Scanner scanner = ((OnlineBankApplication)app).getScanner();
		TransactionService ts = ((OnlineBankApplication)app).getTransactionService();
		boolean valid_amount = false;
		boolean not_overdrawn = false;
		double balance = ((BankCustomerUser)user).getBalance();
		double amount = 0;
		while(!not_overdrawn) {
			while(!valid_amount) {
				System.out.print("How much money would you like to withdraw? $");
				try {
					amount = scanner.nextDouble();
				} catch (InputMismatchException e) {
					scanner.next();
				}
				
				if(amount <= 0) {
					System.out.println("That is not a valid amount. Please enter a number between 0.01 and your maximum account balance.");
				} else {
					valid_amount = true;
				}
			}
			System.out.println("Withdrawing $" + amount + " from account.");
			user = ts.transact('w', amount, user);
			if(((BankCustomerUser)user).getBalance() == balance) {
				System.out.println("Amount too high, balance exceeded. Enter a new amount less than $" + balance);
				valid_amount = false;
			} else {
				not_overdrawn = true;
			}
		}
		System.out.println("New account balance is $" + ((BankCustomerUser)user).getBalance());
		return new CustomerHomeScreen(user);
	}

}
