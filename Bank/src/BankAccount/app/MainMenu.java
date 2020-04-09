package BankAccount.app;

import java.text.DecimalFormat;
import java.util.Scanner;

public class MainMenu extends functions{
	
	Scanner in = new Scanner(System.in);

    int userChoice;
    boolean quit = false;
    DecimalFormat df = new DecimalFormat("###.##");
    float balance = 0f;
    float amount = 0f;
    
	
	public void runMenu(){

    do {

    	System.out.println("1. Deposit money");
        System.out.println("2. Withdraw money");
        System.out.println("3. Check balance");
        System.out.print("Your choice, 0 to quit: ");
        userChoice = in.nextInt();
        
        switch (userChoice) {
        case 1:
              // deposit money
        	  deposit();
              break;
        case 2:
              // withdraw money
        	  withdraw();
              break;
        case 3:
              // check balance
        	checkBalance();
        	break;
        case 0:
              quit = true;
              break;

        default:
              System.out.println("Wrong choice.");
              break;
        }
        System.out.println();
    } while (!quit);
    System.out.println("Thanks for using our bank!");
	}
	
	

}
