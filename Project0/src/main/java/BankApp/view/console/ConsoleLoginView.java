package BankApp.view.console;

import BankApp.controller.AuthenticationController;
import BankApp.model.User;
import BankApp.view.BankAppView;

import java.util.Scanner;

public class ConsoleLoginView extends BankAppView {

    public static User user;

    @Override
    public void lunch() {
        String password;
        String username;

        Scanner sc = new Scanner(System.in);
        boolean login = true;

        //while loop which will run the login menu
        while (login) {
            //user input
            System.out.println(" ------------------ \n" + "Username: \n");
            username = sc.nextLine();
            System.out.println("Password: \n");
            password = sc.nextLine();

            user = authenticationController.login(username,password);
            //if user and pass is valid then login else try again
            if (user != null) {
                login = false;
                System.out.println("\n" +"You have logged in. \n");
                new MainMenuVliew(user).lunch();
            } else {
                System.out.println("\n" +"Incorrect username or password \n");
            }
        }
    }
}
