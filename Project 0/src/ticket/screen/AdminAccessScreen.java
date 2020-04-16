package ticket.screen;

import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.TicketDAO;
import ticket.model.Admin;
import ticket.model.Ticket;

public class AdminAccessScreen implements Screen {
	
	Admin admin;
	
	public AdminAccessScreen(Admin admin) {
		this.admin = admin;
	}
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		TicketDAO ticketDAO = ((TicketApplication)app).getTicketDAO();
		int choice = -1;
		
		List<Ticket> tickets = ticketDAO.getAllTickets();
		System.out.println();
		System.out.println("----- TICKETS -----");
		if (tickets.size() == 0) {
			System.out.println("There are no tickets.");
		} else {
			for (int i = 1; i <= tickets.size(); i++) {
				Ticket ticket = tickets.get(i);
				System.out.print(i + ".   " + ticket.getStatus() + "   " + ticket.getTitle() + "\n");
			}
		}
		System.out.print("\n>");
		
		while (true) {
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			if (choice > 0 && choice <= tickets.size()) {
				return new ViewTicketScreen(tickets.get(choice - 1), admin);
			}
			System.out.print(">");
		}	
	}
}
