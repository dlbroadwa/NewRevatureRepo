package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.TicketDAO;
import ticket.dao.UserDAO;
import ticket.model.Admin;
import ticket.model.Ticket;
import ticket.model.User;

public class ViewTicketScreen implements Screen {
	
	private Ticket ticket;
	private User user;
	private Admin admin;
	
	public ViewTicketScreen(Ticket ticket, User user) {
		this.ticket = ticket;
		this.user = user;
	}
	
	public ViewTicketScreen(Ticket ticket, Admin admin) {
		this.ticket = ticket;
		this.admin = admin;
	}
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		User creator = userDAO.getUser(ticket.getUserId());
		int choice = -1;
		
		System.out.println();
		System.out.println("ID: " + ticket.getTicketId());
		System.out.println("Opened by: " + creator.getFirstName() + " " + creator.getLastName() + " on " + ticket.getCreationDate());
		System.out.println("Status: " + ticket.getStatus() + " | Priority: " + ticket.getPriority());
		System.out.println("~~~ " + ticket.getTitle() + " ~~~");
		System.out.println(ticket.getBody());
		System.out.println();
		System.out.println("1. Reply");
		if (admin != null) {
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
					if (user != null)
						return new ReplyTicketScreen(ticket, user);
					else if (admin != null)
						return new ReplyTicketScreen(ticket, admin);
					else
						return null;
				case 2:
					if (user != null)
						break;
					int statusChoice = -1;
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
					if (user != null)
						break;
					int priorityChoice = -1;
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
					if (user != null)
						break;
					int deleteChoice = -1;
					System.out.println("Are you sure you want to delete this ticket?");
					System.out.println("1. Yes");
					System.out.print("2. No\n\n>");
					while (true) {
						if (scan.hasNextInt())
							deleteChoice = scan.nextInt();
						scan.nextLine();
						if (deleteChoice == 1) {
							ticketDAO.deleteTicket(ticket);
							System.out.println("Ticket successfully deleted.");
							return new AdminAccessScreen(admin);
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
					if (user != null)
						return new UserAccessScreen(user);
					else if (admin != null)
						return new AdminAccessScreen(admin);
					else
						return null;
			}
		}
	}
}
