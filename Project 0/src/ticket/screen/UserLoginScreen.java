package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.UserDAO;
import ticket.model.User;

public class UserLoginScreen implements Screen {
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		
		System.out.println();
		System.out.print("Please enter your user id: ");
		
		while (true) {
			String username = scan.nextLine();
			User user = userDAO.getUser(username);
			if (user == null) {
				System.out.println("****Error**** user id not registered");
				return new UserLoginScreen();
			} else {
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!user.getPassword().equals(pass)) {
					System.out.println("****Error**** wrong password");
					System.out.println();
					return new WelcomeScreen();
				} else {
					return new UserAccessScreen(user);
				}
			}
		}	
	}
}
