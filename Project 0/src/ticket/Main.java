package ticket;

import ticket.app.Application;
import ticket.app.TicketApplication;

/**
 * Main --- Runs the Ticket Application.
 * @author Austin Kind
 */
public class Main {

	/**
	 * Runs the Ticket Application.
	 * @param args	Not used.
	 */
	public static void main(String[] args) {
		Application app = new TicketApplication();
		app.run();
	}
}