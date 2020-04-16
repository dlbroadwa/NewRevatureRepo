package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.UserDAO;
import ticket.model.User;
import ticket.utilities.Encryption;

public class UserLoginScreen implements Screen {
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		
		System.out.println();
		System.out.println("Please enter your user id.");
		System.out.print("1. Go back.\n\n>");
		
		while (true) {
			String username = scan.nextLine();
			
			try {
				int choice = Integer.parseInt(username);
				if (choice == 1) {
					System.out.println();
					return new WelcomeScreen();
				}
			} catch (NumberFormatException e) {}
			
			User user = userDAO.getUser(username);
			if (user == null) {
				System.out.println("\n****Error**** user id not registered");
				return new UserLoginScreen();
			} else {
				System.out.println();
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!user.getPassword().equals(Encryption.encrypt(pass))) {
					System.out.println("\n****Error**** wrong password");
					System.out.println();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return new WelcomeScreen();
				} else {
					return new UserAccessScreen(user);
				}
			}
		}	
	}
}
