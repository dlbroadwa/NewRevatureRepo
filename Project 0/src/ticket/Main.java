package ticket;

import ticket.app.Application;
import ticket.app.TicketApplication;

public class Main {

	public static void main(String[] args) {
		Application app = new TicketApplication();
		app.run();
	}
}