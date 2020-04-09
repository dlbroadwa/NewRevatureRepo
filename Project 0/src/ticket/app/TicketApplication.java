package ticket.app;

import java.util.Scanner;

import ticket.dao.*;
import ticket.screen.*;

public class TicketApplication extends Application {
	
	Scanner scan;
	TicketDAO ticketDAO;
	UserDAO userDAO;
	AdminDAO adminDAO;
	Screen screen;
	
	public TicketApplication() {
		scan = new Scanner(System.in);
		ticketDAO = new TicketDAOFileIO();
		userDAO = new UserDAOFileIO();
		adminDAO = new AdminDAOFileIO();
		screen = new WelcomeScreen();
	}
	
	public void run() {
		while (screen != null) {
			screen = screen.doScreen(this);
		}
		System.out.println("\nClosing...");
		scan.close();
	}
	
	public Scanner getScanner() {
		return this.scan;
	}
	
	public TicketDAO getTicketDAO() {
		return ticketDAO;
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public AdminDAO getAdminDAO() {
		return adminDAO;
	}
}
