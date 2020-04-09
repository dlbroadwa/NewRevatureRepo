package ticket.screen;

import java.util.Scanner;

import ticket.app.Application;
import ticket.model.Admin;
import ticket.app.TicketApplication;
import ticket.dao.AdminDAO;

public class AdminLoginScreen implements Screen {
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		AdminDAO adminDAO = ((TicketApplication)app).getAdminDAO();
		
		System.out.println();
		System.out.print("Please enter your admin id: ");
		while (true) {
			String username = scan.nextLine();
			Admin admin = adminDAO.getAdmin(username);
			if (admin == null) {
				System.out.println("****Error**** admin id not valid");
				return new AdminLoginScreen();
			} else {
				System.out.print("Please enter your password: ");
				String pass = scan.nextLine();
				if (!admin.getPassword().equals(pass)) {
					System.out.println("****Error**** wrong password");
					System.out.println();
					return new WelcomeScreen();
				} else {
					return new AdminAccessScreen(admin);
				}
			}
		}
	}
}
