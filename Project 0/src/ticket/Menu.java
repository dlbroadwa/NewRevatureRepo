package ticket;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import ticket.dao.*;
import ticket.model.Admin;
import ticket.model.Ticket;
import ticket.model.User;

public class Menu {
	
	TicketDAO ticketDAO;
	UserDAO userDAO;
	AdminDAO adminDAO;
	Scanner scan;
	
	public Menu() {
		ticketDAO = new TicketDAOFileIO();
		userDAO = new UserDAOFileIO();
		adminDAO = new AdminDAOFileIO();
		scan = new Scanner(System.in);
		
	}
	
	public void start() {
		boolean exit = false;
		int choice = 0;
		System.out.println("Welcome to our Help Desk!");
		System.out.println("1. Login as a user.");
		System.out.println("2. Login as an admin.");
		System.out.println("3. Register as a user.");
		System.out.print("4. Exit\n>");
		while(!exit) {
			if (scan.hasNextInt())
				choice = scan.nextInt();
			scan.nextLine();
			switch (choice) {
				case 1:
					exit = true;
					loginAsUser();
					break;
				case 2:
					exit = true;
					loginAsAdmin();
					break;
				case 3:
					exit = true;
					register();
					break;
				case 4:
					exit = true;
					exit();
					break;
			}
		}
	}
	
	private void loginAsUser() {
		boolean exit = false;
		System.out.println();
		System.out.print("Please enter your user id: ");
		while (!exit) {
			String username = scan.nextLine();
			User user = userDAO.getUser(username);
			if (user == null) {
				System.out.println("****Error**** user id not registered");
				exit = true;
				loginAsUser();
			} else {
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!user.getPassword().equals(pass)) {
					System.out.println("****Error**** wrong password");
					exit = true;
					System.out.println();
					start();
				} else {
					exit = true;
					userAccess(user);
				}
			}
		}		
	}
	
	private void userAccess(User user) {
		List<Ticket> tickets = ticketDAO.getTicketsByUser(user.getId());
		System.out.println();
		System.out.println("----- TICKETS -----");
		for (int i = 1; i <= tickets.size(); i++) {
			Ticket ticket = tickets.get(i);
			System.out.print(i + ".   " + ticket.getStatus() + "   " + ticket.getTitle());
		}
		System.out.println();
		System.out.print("0. Open a new ticket.\n>");
		while (true) {}
	}
	
	private void openTicket(User user) {
		
	}
	
	private void loginAsAdmin() {
		boolean exit = false;
		System.out.println();
		System.out.print("Please enter your admin id: ");
		while (!exit) {
			String username = scan.nextLine();
			Admin admin = adminDAO.getAdmin(username);
			if (admin == null) {
				System.out.println("****Error**** admin id not valid");
				exit = true;
				loginAsAdmin();
			} else {
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!admin.getPassword().equals(pass)) {
					System.out.println("****Error**** wrong password");
					exit = true;
					System.out.println();
					start();
				} else {
					exit = true;
					adminAccess(admin);
				}
			}
		}
	}
	
	private void adminAccess(Admin admin) {
		System.out.println();
		System.out.println("----- CURRENT TICKETS -----");
		while (true) {}
	}
	
	private void register() {
		
	}

	private void exit() {
		scan.close();
	}
}
