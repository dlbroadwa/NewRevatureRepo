package ticket.screen;

import java.util.Scanner;
import java.util.regex.Pattern;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.UserDAO;
import ticket.model.User;
import ticket.utilities.Utilities;

public class RegisterScreen implements Screen {
	
	String user_id;
	String password;
	String password2;
	String first_name;
	String last_name;
	String email;
	
	public Screen doScreen(Application app) {
		
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		
		while (true) {
			System.out.println();
			System.out.print("Enter your desired user id (between 3-12 characters): ");
			user_id = scan.nextLine();
			if (Utilities.isValidUserID(user_id)) {
				if (userDAO.getUser(user_id) == null) {		
					while (true) {
						System.out.println();
						System.out.print("Enter your password: ");
						password = scan.nextLine();
						if (Utilities.isValidPassword(password)) {
							System.out.print("Please re-enter your password: ");
							password2 = scan.nextLine();
							if (password2.equals(password)) {
								while (true) {
									System.out.println();
									System.out.print("Please enter your first name: ");
									first_name = scan.nextLine();
									if (Utilities.isValidName(first_name)) {
										while (true) {
											System.out.print("Please enter your last name: ");
											last_name = scan.nextLine();
											if (Utilities.isValidName(last_name)) {
												while (true) {
													System.out.println();
													System.out.print("Please enter your email: ");
													email = scan.nextLine();
													if (Utilities.isValidEmail(email)) {
														userDAO.addUser(new User(user_id, password, first_name, last_name, email));
														System.out.println();
														System.out.println("Successfully registered.");
														return new WelcomeScreen();
													} else {
														System.out.println("\n****ERROR**** invalid email");
													}
												}
											} else {
												System.out.println("\n****ERROR**** name must only contain alphabetic characters");
											}
										}
									} else {
										System.out.println("\n****ERROR**** name must only contain alphabetic characters");
									}
								}
							} else {
								System.out.println("\n****ERROR**** passwords do not match");
							}
						} else {
							System.out.println("\n****ERROR**** a password must be at least 6 characters long");
						}
					}
				} else {
					System.out.println("\n****ERROR**** a user with that id is already registered");
				}
			} else {
				System.out.println("\n****ERROR**** a user id must be between 3-12 characters and only contain alphanumeric characters plus underscores");
			}	
		}
	}
}
