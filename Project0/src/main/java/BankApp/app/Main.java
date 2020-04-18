package BankApp.app;

/********************************************
 * Project 0
 * by Rayan Vakil
 *
 * Welcome to my small banking program!
 *
 * This program is filled with multiple functionalities
 * It can connect to a database, mine specifically, which
 * includes one table with an id column which is the primary key
 * username column, password column, and balance column
 * In my database I am only using 3 regular users
 * There is no admin or special privileges
 *
 * Classes:
 * ---------
 * Main - entry point of program
 * functions - Takes care of withdraw, deposit, and check balance
 * 			functionality
 * MainMenu - Sets up all functionality for the menu
 * userAuthentication - Sets up user authentication functionality
 * 					on the login screen
 * SqlDAO - used to setup connection with the database
 * UserDAO - used to get the user from the database
 * User - creates the user variables used in the program
 ********************************************/


//Main class which runs the whole program
public class Main {

    public static void main(String[] args) {


        //create instance of class userAuthentication
        UserAuthentication user = new UserAuthentication();


        //call the method from the class to start the program
        user.UserAuth();

    }
}