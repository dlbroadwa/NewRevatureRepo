package ticket.screen;

import java.time.ZonedDateTime;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.PostDAO;
import ticket.dao.TicketDAO;
import ticket.model.Post;
import ticket.model.Ticket;
import ticket.model.User;

public class ReplyTicketScreen implements Screen {
	
	private Ticket ticket;
	private User user;
	
	public ReplyTicketScreen(Ticket ticket, User user) {
		this.ticket = ticket;
		this.user = user;
	}
	
	public Screen doScreen(Application app) {
		Scanner scan = ((TicketApplication)app).getScanner();
		PostDAO postDAO = ((TicketApplication)app).getPostDAO();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		
		System.out.println();
		System.out.println("Enter your reply.");
		System.out.print("1. Go back.\n\n>");
		String reply = scan.nextLine();
		try {
			int choice = Integer.parseInt(reply);
			if (choice == 1) 
				return new ViewTicketScreen(ticket, user);
		} catch (NumberFormatException e) {}
		
		postDAO.addPost(new Post(ticket.getTicketId(), user.getId(), postDAO.getNextPostNumber(ticket.getTicketId()), reply));	
		ticket.setStatus(Ticket.STATUS_OPEN);
		ticket.setLastUpdated(ZonedDateTime.now());
		ticketDAO.updateTicket(ticket);
		
		System.out.println();
		System.out.println("Reply successfully posted.");
		
		return new ViewTicketScreen(ticket, user);
	}
}
