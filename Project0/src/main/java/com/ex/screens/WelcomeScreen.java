package main.java.com.ex.screens;

import java.util.Scanner;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;
import main.java.com.ex.users.User;
import main.java.com.services.ValidationService;

/**
 * WelcomeScreen: class that implements Screen to display a welcome message and allow for the login procedure
 * if the user is valid a home screen is created for the type of user that logged into the application
 * 
 * @author jtb12_000
 *
 */

public class WelcomeScreen implements Screen {

	@Override
	public Screen display(Application app) {
		System.out.println("Welcome to the Online Bank Application");
		while(true) {
			Scanner scanner = ((OnlineBankApplication)app).getScanner();
			String username;
			String password;
			ValidationService vs = ((OnlineBankApplication)app).getValidationService();
			
			System.out.print("Please Enter Your Username: ");
			username = scanner.next();
			System.out.print("Please Enter Your Password: ");
			password = scanner.next();
			
			User user = vs.validate_username_password(username, password);
			
			if(user != null) {
				if(user.getAccess_level() == 'c') {
					CustomerHomeScreen cs = new CustomerHomeScreen(user);
					return cs;
				}else {
					TellerHomeScreen ts = new TellerHomeScreen(user);
					return ts;
				}
			}else {
				System.out.println("\nInvalid username/password\n");
			}
		}
	}

}
