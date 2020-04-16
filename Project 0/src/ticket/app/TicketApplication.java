package ticket.app;

import java.util.Scanner;

import ticket.dao.*;
import ticket.screen.*;
import ticket.utilities.ConnectionUtil;
import ticket.utilities.PostgresConnectionUtil;

public class TicketApplication extends Application {
	
	Scanner scan;
	TicketDAO ticketDAO;
	UserDAO userDAO;
	AdminDAO adminDAO;
	Screen screen;
	ConnectionUtil connectionUtil;
	
	public TicketApplication() {
		connectionUtil = new PostgresConnectionUtil();
		scan = new Scanner(System.in);
		ticketDAO = new TicketDAOSQLImpl(connectionUtil);
		userDAO = new UserDAOSQLImpl(connectionUtil);
		adminDAO = new AdminDAOSQLImpl(connectionUtil);
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
	
	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}
}
