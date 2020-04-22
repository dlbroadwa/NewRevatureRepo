package ticket.app;

import java.util.Scanner;

import ticket.dao.*;
import ticket.screen.*;
import ticket.utilities.ConnectionUtil;
import ticket.utilities.PostgresConnectionUtil;

/**
 * TicketApplication --- Runs the helpdesk ticketing application.
 * @author Austin Kind
 */
public class TicketApplication extends Application {
	
	Scanner scan;
	TicketDAO ticketDAO;
	UserDAO userDAO;
	PostDAO postDAO;
	Screen screen;
	ConnectionUtil connectionUtil;
	
	/**
	 * Constructs the object. Establishes the connection, scanner, and DAOs.
	 */
	public TicketApplication() {
		connectionUtil = new PostgresConnectionUtil();
		scan = new Scanner(System.in);
		ticketDAO = new TicketDAOSQLImpl(connectionUtil);
		userDAO = new UserDAOSQLImpl(connectionUtil);
		postDAO = new PostDAOSQLImpl(connectionUtil);
		screen = new WelcomeScreen();	
	}
	
	/**
	 * Passes Screens back and forth until one returns null.
	 * @return 		No return type.
	 */
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
	
	public PostDAO getPostDAO() {
		return postDAO;
	}
	
	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}
}
