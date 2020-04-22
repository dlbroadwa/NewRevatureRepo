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
 * UserAccessScreen --- Displays the given user's tickets and allows the user to view them or open a new one.
 * @author Austin Kind
 */
public class UserAccessScreen implements Screen {
	
	User user;
	DateTimeFormatter dateFormat;
	
	/**
	 * Constructs the object.
	 * @param user	The user that wants to view their tickets.
	 */
	public UserAccessScreen(User user) {
		this.user = user;
		dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a");
	}
	
	/**
	 * Displays the given user's tickets, showing them the title, status, priority, and the last date
	 * they were updated. The user is able to view the ticket or choose to open a new one.
	 * @param app	The application running.
	 * @return 		OpenTicketScreen, ViewTicketScreen, or WelcomeScreen.
	 */
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		int choice = -1;
		
		List<Ticket> tickets = ticketDAO.getTicketsByUser(user.getId());
		System.out.println();
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("//                                            YOUR TICKETS                                            //");
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
		System.out.println("0. Log off.");
		System.out.print("N. Open a new ticket.\n\n>");
		
		while (true) {
			String answer = scan.nextLine();
			try {
				choice = Integer.parseInt(answer);
			} catch (NumberFormatException e) {}
			
			if (answer.equals("N"))
				return new OpenTicketScreen(user);
			
			if (choice == 0) {
				System.out.println();
				return new WelcomeScreen();
			} else if (choice > 0 && choice <= tickets.size()) {
				return new ViewTicketScreen(tickets.get(choice - 1), user);
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
