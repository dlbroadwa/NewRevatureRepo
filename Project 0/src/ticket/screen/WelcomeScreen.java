package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;

/**
 * WelcomeScreen --- Displays the Welcome Screen and asks the user if it wants to login, register, or exit.
 * @author Austin Kind
 */
public class WelcomeScreen implements Screen {
	
	/**
	 * Displays the Welcome Screen and asks the user if it wants to login, register, or exit.
	 * @param app	The application running.
	 * @return 		UserLoginScreen, RegisterScreen, or null.
	 */
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		int choice = 0;
		
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println(" Welcome to our Help Desk!");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("1. Login as a user.");
		System.out.println("2. Register as a user.");
		System.out.print("3. Exit.\n\n>");
		
		while(true) {
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
				case 1:
					return new UserLoginScreen();
				case 2:
					return new RegisterScreen();
				case 3:
					return null;
				default:
					System.out.print(">");
			}
		}
	}
}
