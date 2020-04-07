package BankAccount.DAO;
import java.util.Scanner;

import BankAccount.app.MainMenu; 

public class FileIoDAO extends DAO {
	
			
		    public void userAuth()
		    {	
		    	MainMenu menu = new MainMenu();
		        String username, password;
		        Scanner s = new Scanner(System.in);
		        System.out.print("Enter username:");//username:user
		        username = s.nextLine();
		        System.out.print("Enter password:");//password:user
		        password = s.nextLine();
		        if(username.equals("user") && password.equals("user"))
		        {
		            System.out.println("Authentication Successful");
		            menu.runMenu();
		            
		        }
		        else
		        {
		            System.out.println("Authentication Failed");
		        }
		    }


}
