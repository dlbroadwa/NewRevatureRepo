package BankAccount.app;

import java.util.Scanner;

public class userAuthentication {
	
	 public void userAuth()
	    {	
	    	MainMenu menu = new MainMenu();
	    	String password = "user";
			String loginPass;
			String username = "user";
			String loginUser;
	    	Scanner sc = new Scanner(System.in);

	    	boolean login = true;
	    	boolean checkvalid = false;
	        
	        
	   
	        while (login) {
	            checkvalid = false;
	            System.out.println(" ------------------ \n" + "Username: \n");
	            loginUser = sc.nextLine();
	            System.out.println("Password: \n");
	            loginPass = sc.nextLine();

	            if (loginUser.equals(username) && (loginPass.equals(password))) {
	                checkvalid = true;
	                login = false;
	                System.out.println("You have logged in. \n");
	                menu.runMenu();
	            } else {
	                checkvalid = false;
	                System.out.println("Incorrect username or password \n");
	            }
	        }
	    }

}
