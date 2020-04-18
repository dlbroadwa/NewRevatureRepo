package BankApp.app;

import java.util.Scanner;
import BankApp.DAO.*;
import BankApp.model.User;


/*****************************************************
 *
 * This class will be used to authenticate user
 * during login
 *
 * Methods:
 *
 * userAuth - contains login functionality
 *
 ****************************************************/
public class UserAuthentication {

    public static User user;

    private final UserDAO authenticationDAO;

    //create instance of UserDAO so we can use the methods from there
    public UserAuthentication() {
        authenticationDAO = new UserDAO();
    }

    public static int getUserId() {
        return user.getId();
    }

    //contains user login functionality
    public void UserAuth()
    {
        //variable declarations
        MainMenu menu = new MainMenu();
        String password;
        String username;
        Scanner sc = new Scanner(System.in);
        boolean login = true;
        boolean checkvalid = false;



        //while loop which will run the login menu
        while (login) {

            //user input
            checkvalid = false;
            System.out.println(" ------------------ \n" + "Username: \n");
            username = sc.nextLine();
            System.out.println("Password: \n");
            password = sc.nextLine();

            //if user and pass is valid then login else try again
            if (authenticationDAO.validUser(username, password)) {
                checkvalid = true;
                login = false;
                user = authenticationDAO.getUser(username, password);
                System.out.println("\n" +"You have logged in. \n");
                menu.runMenu();
            } else {
                checkvalid = false;
                System.out.println("\n" +"Incorrect username or password \n");
            }
        }//end while



    }

}