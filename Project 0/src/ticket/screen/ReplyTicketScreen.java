package ticket.screen;

import java.time.LocalDateTime;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.PostDAO;
import ticket.dao.TicketDAO;
import ticket.model.Post;
import ticket.model.Ticket;
import ticket.model.User;

/**
 * ReplyTicketScreen --- Displays a screen that asks the user to enter their reply, then adds the reply to the ticket's reply chain.
 * @author Austin Kind
 */
public class ReplyTicketScreen implements Screen {
	
	private Ticket ticket;
	private User user;
	
	/**
	 * Constructs the object.
	 * @param ticket	The ticket that the user is replying to.
	 * @param user		The user that is replying.
	 */
	public ReplyTicketScreen(Ticket ticket, User user) {
		this.ticket = ticket;
		this.user = user;
	}
	
	/**
	 * Displays a screen that asks the user to enter their reply, then adds the reply to the ticket's reply chain.
	 * @param app	The application running.
	 * @return 		ViewTicketScreen.
	 */
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
		ticket.setLastUpdated(LocalDateTime.now());
		ticketDAO.updateTicket(ticket);
		
		System.out.println();
		System.out.println("Reply successfully posted.");
		
		return new ViewTicketScreen(ticket, user);
	}
}
