package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.model.Admin;
import ticket.utilities.Encryption;
import ticket.app.TicketApplication;
import ticket.dao.AdminDAO;

public class AdminLoginScreen implements Screen {
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		AdminDAO adminDAO = ((TicketApplication)app).getAdminDAO();
		
		System.out.println();
		System.out.println("Please enter your admin id.");
		System.out.print("1. Go back.\n\n>");
		
		while (true) {
			String username = scan.nextLine();
			
			try {
				int choice = Integer.parseInt(username);
				if (choice == 1) {
					System.out.println();
					return new WelcomeScreen();
				}
			} catch (NumberFormatException e) {}
			
			Admin admin = adminDAO.getAdmin(username);
			if (admin == null) {
				System.out.println("\n****Error**** admin id not valid");
				return new AdminLoginScreen();
			} else {
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!admin.getPassword().equals(Encryption.encrypt(pass))) {
					System.out.println("\n****Error**** wrong password");
					System.out.println();
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					return new WelcomeScreen();
				} else {
					return new AdminAccessScreen(admin);
				}
			}
		}
	}
}
