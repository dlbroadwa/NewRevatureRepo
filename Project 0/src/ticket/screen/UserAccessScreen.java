package ticket.screen;

import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.TicketDAO;
import ticket.model.Ticket;
import ticket.model.User;

public class UserAccessScreen implements Screen {
	
	User user;
	
	public UserAccessScreen(User user) {
		this.user = user;
	}
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		
		List<Ticket> tickets = ticketDAO.getTicketsByUser(user.getId());
		System.out.println();
		System.out.println("----- TICKETS -----");
		for (int i = 1; i <= tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			System.out.print(i + ".   " + ticket.getStatus() + "   " + ticket.getTitle());
		}
		System.out.println();
		System.out.print("0. Open a new ticket.\n>");
		
		while (true) {
			//TODO
			return null;
		}
	}
}
