package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.PostDAO;
import ticket.dao.TicketDAO;
import ticket.model.Post;
import ticket.model.Ticket;
import ticket.model.User;

public class OpenTicketScreen implements Screen {

	private User user;
	
	public OpenTicketScreen(User user) {
		this.user = user;
	}
	
	public Screen doScreen(Application app) {
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		PostDAO postDAO = ((TicketApplication)app).getPostDAO();
		
		title:
		while (true) {
			System.out.println();
			System.out.println("Enter a title for your ticket.");
			System.out.print("1. Go back.\n\n>");
			String title = scan.nextLine();
			try {
				int choice = Integer.parseInt(title);
				if (choice == 1) {
					return new UserAccessScreen(user);
				}
			} catch (NumberFormatException e) {}
			if (title.length() > 100) {
				System.out.println("\n****ERROR**** title cannot be longer than 100 characters\n");
				continue;
			}
			while (true) {
				System.out.println();
				System.out.println("Write your post.");
				System.out.print("1. Go back.\n\n>");
				String body = scan.nextLine();
				try {
					int choice = Integer.parseInt(body);
					if (choice == 1) {
						System.out.println();
						continue title;
					}
				} catch (NumberFormatException e) {}
				if (body.length() > 5000) {
					System.out.println("\n****ERROR**** post cannot be longer than 5000 characters\n");
					continue;
				} else {
					int ticket_id = ticketDAO.getNextTicketId();
					ticketDAO.addTicket(new Ticket(ticket_id, user.getId(), title));
					postDAO.addPost(new Post(ticket_id, user.getId(), postDAO.getNextPostNumber(ticket_id), body));
					System.out.println();
					System.out.println("Ticket successfully created.\n");
					return new UserAccessScreen(user);
				}
			}
		}
	}
}
