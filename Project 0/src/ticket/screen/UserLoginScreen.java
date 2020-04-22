package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.UserDAO;
import ticket.model.User;
import ticket.utilities.Encryption;

/**
 * UserLoginScreen --- Displays the User Login Screen and allows the user to login.
 * @author Austin Kind
 */
public class UserLoginScreen implements Screen {
	
	/**
	 * Displays the User Login Screen and allows the user to login, giving them
	 * user access if they are a normal user, or admin access if they are an admin.
	 * @param app	The application running.
	 * @return 		UserAccessScreen or AdminAccessScreen.
	 */
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();

		while (true) {
			System.out.println();
			System.out.println("Please enter your user id.");
			System.out.print("1. Go back.\n\n>");
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
				System.out.println("Please enter your password.");
				System.out.print("1. Go back.\n\n>");
				String pass = scan.nextLine();
				
				try {
					int choice = Integer.parseInt(pass);
					if (choice == 1)
						continue;
				} catch (NumberFormatException e) {}
				
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
					if (user.isAdmin())
						return new AdminAccessScreen(user);
					else
						return new UserAccessScreen(user);
				}
			}
		}	
	}
}
