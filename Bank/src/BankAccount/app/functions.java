package BankAccount.app;

import java.text.DecimalFormat;
import java.util.Scanner;

public class functions {
	
	Scanner in = new Scanner(System.in);

    int userChoice;
    boolean quit = false;
    DecimalFormat df = new DecimalFormat("###.##");
    float balance = 0f;
    float amount = 0f;
	
	
	//method for depositing money
		public void deposit() {
			
	        System.out.print("Amount to deposit: ");
	        amount = in.nextFloat();

	        if (amount <= 0)
	             System.out.println("Can't deposit nonpositive amount.");
	        else {
	             balance += amount;
	             System.out.println("$" + amount + " has been deposited.");
	        }
			
		}
		
		public void withdraw() {
			System.out.print("Amount to withdraw: ");
	        amount = in.nextFloat();

	        if (amount <= 0 || amount > balance)
	             System.out.println("Withdrawal can't be completed.");
	        else {
	             balance -= amount;
	             System.out.println("$" + amount + " has been withdrawn.");
	        }
		}
		
		public void checkBalance() {
			
            System.out.println("Your balance: $" + df.format(balance));

			
		}

}
