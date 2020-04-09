package ticket.screen;

import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.TicketDAO;
import ticket.dao.TicketDAOFileIO;
import ticket.model.Admin;
import ticket.model.Ticket;

public class AdminAccessScreen implements Screen {
	
	Admin admin;
	
	public AdminAccessScreen(Admin admin) {
		this.admin = admin;
	}
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = new TicketDAOFileIO();
		
		List<Ticket> tickets = ticketDAO.getAllTickets();
		System.out.println();
		System.out.println("----- TICKETS -----");
		for (int i = 1; i <= tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			System.out.print(i + ".   " + ticket.getStatus() + "   " + ticket.getTitle());
		}
		while (true) {
			//TODO
			return null;
		}	
	}
}
