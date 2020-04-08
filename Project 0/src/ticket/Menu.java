package ticket;

import java.util.Scanner;
import ticket.dao.*;

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
			}
		}
	}
	
	private void loginAsUser() {
		boolean exit = false;
		System.out.println();
		System.out.println("Please enter your user id:");
		System.out.println("1. Go back.");
		while (!exit) {
			if (scan.hasNextInt()) {
				if (scan.nextInt() == 1) {
					scan.nextLine();
					exit = true;
					System.out.println();
					start();
				}	
			} else {
				String username = scan.nextLine();
				User user = userDAO.getUser(username);
				if (user == null) {
					System.out.println("****Error**** user id not registered");
					exit = true;
					loginAsUser();
				} else {
					System.out.println("Please enter your password:");
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
	}
	
	private void userAccess(User user) {
		System.out.println();
		System.out.println("----- CURRENT TICKETS -----");
		while (true) {}
	}
	
	private void loginAsAdmin() {
		boolean exit = false;
		System.out.println();
		System.out.println("Please enter your admin id:");
		System.out.println("1. Go back.");
		while (!exit) {
			if (scan.hasNextInt()) {
				if (scan.nextInt() == 1) {
					scan.nextLine();
					exit = true;
					System.out.println();
					start();
				}	
			} else {
				String username = scan.nextLine();
				Admin admin = adminDAO.getAdmin(username);
				if (admin == null) {
					System.out.println("****Error**** admin id not valid");
					exit = true;
					loginAsAdmin();
				} else {
					System.out.println("Please enter your password:");
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
	}
	
	private void adminAccess(Admin admin) {
		System.out.println();
		System.out.println("----- CURRENT TICKETS -----");
		while (true) {}
	}
	
	private void register() {
		
	}
}
