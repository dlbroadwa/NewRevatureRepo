package ticket.screen;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.PostDAO;
import ticket.dao.TicketDAO;
import ticket.dao.UserDAO;
import ticket.model.Post;
import ticket.model.Ticket;
import ticket.model.User;
import ticket.utilities.Regex;

/**
 * ViewTicketScreen --- Displays the given Ticket and allows the user to reply or, if they have admin access, change attributes of the ticket.
 * @author Austin Kind
 */
public class ViewTicketScreen implements Screen {
	
	private Ticket ticket;
	private User user;
	DateTimeFormatter dateFormat;
	
	/**
	 * Constructs the object.
	 * @param ticket	The ticket to be viewed.
	 * @param user		The user that is viewing the ticket.
	 */
	public ViewTicketScreen(Ticket ticket, User user) {
		this.ticket = ticket;
		this.user = user;
		dateFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a");
	}
	
	/**
	 * Displays the given Ticket and allows the user to reply,
	 * in addition, if they have admin access they are able to
	 * change the status or priority of the ticket and delete
	 * it as well.
	 * @param app	The application running.
	 * @return 		UserAccessScreen, AdminAccessScreen, or ReplyTicketScreen.
	 */
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		PostDAO postDAO = ((TicketApplication)app).getPostDAO();
		List<Post> posts = postDAO.getPostsFromTicket(ticket.getTicketId());
		User creator = userDAO.getUser(ticket.getUserId());
		int choice = -1;
		
		String ticketLine = " TICKET ID: " + ticket.getTicketId() + " ";
		String creatorLine = " Created by " + creator.getFirstName() + " " + creator.getLastName() + " on " + ticket.getCreationDate().format(dateFormat) + " ";
		String statusLine = " Status: (" + ticket.getStatus() + ")    Priority: (" + ticket.getPriority() + ") ";
		String titleLine = "";
		if (Regex.isWhitespace(ticket.getTitle()))
			titleLine = " (No Title) ";
		else
			titleLine = " " + ticket.getTitle() + " ";
		
		System.out.println();
		System.out.println(stringOfLengthN("~", ticketLine.length()));
		System.out.println(ticketLine);
		System.out.println(stringOfLengthN("~", creatorLine.length()));
		System.out.println(creatorLine);
		if (creatorLine.length() > statusLine.length())
			System.out.println(stringOfLengthN("~", creatorLine.length()));
		else
			System.out.println(stringOfLengthN("~", statusLine.length()));
		System.out.println(statusLine);
		if (statusLine.length() > titleLine.length())
			System.out.println(stringOfLengthN("~", statusLine.length()));
		else
			System.out.println(stringOfLengthN("~", titleLine.length()));
		System.out.println(titleLine);
		System.out.println(stringOfLengthN("~", titleLine.length()));
		System.out.println();
		
		for (Post post : posts) {
			User user = userDAO.getUser(post.getPosterId());
			String top = " ------ " + user.getFirstName() + " " + user.getLastName() + " ------ " + post.getCreationDate().format(dateFormat) + " ------";
			int length = post.getBody().length() + 4;
			int bodySpace = 0;
			if (length < top.length()) {
				bodySpace = top.length() - length + 1;
				length = top.length() + 1;
			}
			int topLine = length - user.getFirstName().length() - user.getLastName().length() - post.getCreationDate().format(dateFormat).length() - 25;
			
			System.out.println(top + stringOfLengthN("-", topLine));
			System.out.println("|" + stringOfLengthN(" ", length - 2) + "|");
			System.out.println("| " + post.getBody() + stringOfLengthN(" ", bodySpace) + " |");
			System.out.println("|" + stringOfLengthN(" ", length - 2) + "|");
			System.out.println(" " + stringOfLengthN("-", length - 2));
			System.out.println();
		}	 
		System.out.println();
		System.out.println("1. Reply");
		if (user.isAdmin()) {
			System.out.println("2. Change status");
			System.out.println("3. Change priority");
			System.out.println("4. Delete ticket");
		}
		System.out.print("0. Go back\n\n>");
		
		ticketChoices:
		while (true) {
			choice = -1;
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			switch(choice) {
				case 1:
					return new ReplyTicketScreen(ticket, user);
				case 2:
					if (!user.isAdmin())
						break;
					int statusChoice = -1;
					System.out.println();
					System.out.println("What would you like to change the status to?");
					System.out.println("1. Open");
					System.out.println("2. Hold");
					System.out.print("3. Closed\n\n>");
					while (true) {
						if (scan.hasNextInt())
							statusChoice = scan.nextInt();
						scan.nextLine();
						switch (statusChoice) {
							case 1:
								ticket.setStatus(Ticket.STATUS_OPEN);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nStatus successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
							case 2:
								ticket.setStatus(Ticket.STATUS_HOLD);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nStatus successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
							case 3:
								ticket.setStatus(Ticket.STATUS_CLOSED);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nStatus successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
						}
					}
				case 3:
					if (!user.isAdmin())
						break;
					int priorityChoice = -1;
					System.out.println();
					System.out.println("What would you like to change the priority to?");
					System.out.println("1. Low");
					System.out.println("2. Medium");
					System.out.print("3. High\n\n>");
					while (true) {
						if (scan.hasNextInt())
							priorityChoice = scan.nextInt();
						scan.nextLine();
						switch (priorityChoice) {
							case 1:
								ticket.setPriority(Ticket.PRIORITY_LOW);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nPriority successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
							case 2:
								ticket.setPriority(Ticket.PRIORITY_MEDIUM);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nPriority successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
							case 3:
								ticket.setPriority(Ticket.PRIORITY_HIGH);
								ticketDAO.updateTicket(ticket);
								System.out.println("\nPriority successfully changed.");
								System.out.println();
								System.out.println("1. Reply");
								System.out.println("2. Change status");
								System.out.println("3. Change priority");
								System.out.println("4. Delete ticket");
								System.out.print("0. Go back\n\n>");
								continue ticketChoices;
						}
					}
				case 4:
					if (!user.isAdmin())
						break;
					int deleteChoice = -1;
					System.out.println();
					System.out.println("Are you sure you want to delete this ticket?");
					System.out.println("1. Yes");
					System.out.print("2. No\n\n>");
					while (true) {
						if (scan.hasNextInt())
							deleteChoice = scan.nextInt();
						scan.nextLine();
						if (deleteChoice == 1) {
							ticketDAO.deleteTicket(ticket);
							System.out.println();
							System.out.println("Ticket successfully deleted.");
							return new AdminAccessScreen(user);
						} else if (deleteChoice == 2) {
							System.out.println();
							System.out.println("1. Reply");
							System.out.println("2. Change status");
							System.out.println("3. Change priority");
							System.out.println("4. Delete ticket");
							System.out.print("0. Go back\n\n>");
							continue ticketChoices;
						}
					}
				case 0:
					if (user.isAdmin())
						return new AdminAccessScreen(user);
					else
						return new UserAccessScreen(user);
			}
		}
	}
	
	/**
	 * Takes the given string and returns that string repeated n times.
	 * @param str	The string to be repeated.
	 * @param n		The number of times the string should be repeated.
	 * @return 		The string repeated n times.
	 */
	private String stringOfLengthN(String str, int n) {
		String result = "";
		for (int i = 0; i < n; i++) {
			result += str;
		}
		return result;
	}
}
