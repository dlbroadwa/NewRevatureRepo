package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;

public class WelcomeScreen implements Screen {
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		int choice = 0;
		
		System.out.println("Welcome to our Help Desk!");
		System.out.println("1. Login as a user.");
		System.out.println("2. Login as an admin.");
		System.out.println("3. Register as a user.");
		System.out.print("4. Exit\n>");
		
		while(true) {
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
				case 1:
					return new UserLoginScreen();
				case 2:
					return new AdminLoginScreen();
				case 3:
					return new RegisterScreen();
				case 4:
					return null;
			}
		}
	}
}
