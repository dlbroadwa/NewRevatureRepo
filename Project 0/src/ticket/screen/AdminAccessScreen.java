package ticket.screen;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.TicketDAO;
import ticket.model.Ticket;
import ticket.model.User;
import ticket.utilities.Regex;

/**
 * AdminAccessScreen --- Displays the all the tickets and lets the admin pick one to view.
 * @author Austin Kind
 */
public class AdminAccessScreen implements Screen {
	
	User admin;
	DateTimeFormatter dateFormat;
	
	/**
	 * Constructs the object.
	 * @param admin		The admin that wants to view all the tickets.
	 */
	public AdminAccessScreen(User admin) {
		this.admin = admin;
		dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a");
	}
	
	/**
	 * Displays the all the tickets, showing the user that created them, the title, the status, 
	 * the priority, and the last date they were updated. The admin is able to view any ticket
	 * they select.
	 * @param app	The application running.
	 * @return 		ViewTicketScreen or WelcomeScreen.
	 */
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		int choice = -1;
		
		List<Ticket> tickets = ticketDAO.getAllTickets();
		System.out.println();
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("//                                              TICKETS                                               //");
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println(" #     User ID                          Title                     Status    Priority     Last Updated");
		System.out.println("--- --------------  --------------------------------------------- ------    --------  -------------------");
		if (tickets.size() == 0) {
			System.out.println("\n                                         You have no tickets.");
		} else {
			for (int i = 1; i <= tickets.size(); i++) {
				Ticket ticket = tickets.get(i-1);
				String result = "";
				if (i > 9)
					result += i + ". ";
				else
					result += i + ".  ";
				result += "[" + ticket.getUserId() + "]  ";
				int nameSpace = 12 - ticket.getUserId().length();
				result += getWhitespace(nameSpace);
				String title = ticket.getTitle();
				if (Regex.isWhitespace(title))
					title = "(No Title)";
				if (title.length() > 45)
					title = title.substring(0, 42) + "...";
				result += title + " ";
				int titleSpace = 45 - title.length();
				result += getWhitespace(titleSpace);
				result += "(" + ticket.getStatus() + ")";
				int statusSpace = 6 - ticket.getStatus().length();
				result += getWhitespace(statusSpace);
				result += " (" + ticket.getPriority() + ")  ";
				int prioritySpace = 6 - ticket.getPriority().length();
				result += getWhitespace(prioritySpace);
				result += ticket.getLastUpdated().format(dateFormat);	
				System.out.println(result);
			}
		}
		System.out.println();
		System.out.print("0. Log off.\n\n>");
		
		while (true) {
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			if (choice == 0) {
				System.out.println();
				return new WelcomeScreen();
			} else if (choice > 0 && choice <= tickets.size()) {
				return new ViewTicketScreen(tickets.get(choice - 1), admin);
			}
			System.out.print(">");
		}	
	}
	
	/**
	 * Takes the given int and returns a string of whitespace repeated n times.
	 * @param n		The number of whitespace to be returned.
	 * @return 		A string of whitespace repeated num times.
	 */
	private String getWhitespace(int n) {
		String result = "";
		for (int i = 0; i < n; i++) {
			result += " ";
		}
		return result;
	}
}
