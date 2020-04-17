package ticket.screen;

import java.util.List;
import java.util.Scanner;

import ticket.app.Application;
import ticket.app.TicketApplication;
import ticket.dao.UserDAO;
import ticket.model.User;
import ticket.utilities.Encryption;
import ticket.utilities.Regex;

public class RegisterScreen implements Screen {
	
	public Screen doScreen(Application app) {
		String user_id;
		String password;
		String password2;
		String first_name;
		String last_name;
		String email;
		Scanner scan = ((TicketApplication)app).getScanner();
		UserDAO userDAO = ((TicketApplication)app).getUserDAO();
		
		id:
		while (true) {
			System.out.println();
			System.out.println("Enter your desired user id. (between 3-12 characters)");
			System.out.print("1. Go back.\n\n>");
			user_id = scan.nextLine();
			
			try {
				int choice = Integer.parseInt(user_id);
				if (choice == 1) {
					System.out.println();
					return new WelcomeScreen();
				}
			} catch (NumberFormatException e) {}
			
			if (Regex.isValidUserID(user_id)) {
				if (userDAO.getUser(user_id) == null) {	
					pass:
					while (true) {
						System.out.println();
						System.out.println("Enter your password.");
						System.out.print("1. Go back.\n\n>");
						password = scan.nextLine();
						
						try {
							int choice = Integer.parseInt(password);
							if (choice == 1) {
								continue id;
							}
						} catch (NumberFormatException e) {}
						
						if (Regex.isValidPassword(password)) {
							System.out.println();
							System.out.print("Please re-enter your password.\n\n>");
							password2 = scan.nextLine();
							if (password2.equals(password)) {
								first_name:
								while (true) {
									System.out.println();
									System.out.println("Please enter your first name.");
									System.out.print("1. Go back.\n\n>");
									first_name = scan.nextLine();
									
									try {
										int choice = Integer.parseInt(first_name);
										if (choice == 1) {
											continue pass;
										}
									} catch (NumberFormatException e) {}
									
									if (Regex.isValidName(first_name)) {
										last_name:
										while (true) {
											System.out.println();
											System.out.println("Please enter your last name.");
											System.out.print("1. Go back.\n\n>");
											last_name = scan.nextLine();
											
											try {
												int choice = Integer.parseInt(last_name);
												if (choice == 1) {
													continue first_name;
												}
											} catch (NumberFormatException e) {}
											
											if (Regex.isValidName(last_name)) {
												email:
												while (true) {
													System.out.println();
													System.out.println("Please enter your email.");
													System.out.print("1. Go back.\n\n>");
													email = scan.nextLine();
													
													try {
														int choice = Integer.parseInt(email);
														if (choice == 1) {
															continue last_name;
														}
													} catch (NumberFormatException e) {}
													
													if (Regex.isValidEmail(email)) {
														
														List<String> emails = userDAO.getEmails();
														for (String e : emails) {
															if (e.equals(email)) {
																System.out.println("\n****ERROR**** email already registered");
																continue email;
															}
														}
														userDAO.addUser(new User(user_id, Encryption.encrypt(password), first_name, last_name, email, false));
														System.out.println();
														System.out.println("Successfully registered.\n");
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
