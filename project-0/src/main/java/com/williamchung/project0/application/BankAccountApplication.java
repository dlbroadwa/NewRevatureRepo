/**
 * Project 0 - William Chung
 * Simple Bank Account simulator for command line
 * Uses Screen interface as View pattern
 * Functionality includes:
 *      Registering and Logging in users,
 *      Depositing and Withdrawing money,
 *      Transfering money to other users.
 * Uses a custom Repository interface to directly access and write to the postgresql database.
 * JDBC driver to connect to said postgresql database.
 */

package com.williamchung.project0.application;

import com.williamchung.project0.models.User;
import com.williamchung.project0.repositories.Repository;
import com.williamchung.project0.repositories.UserRepository;
import com.williamchung.project0.screens.LoginScreen;
import com.williamchung.project0.screens.Screen;
import com.williamchung.project0.services.UserService;
import com.williamchung.project0.utils.ConnectionUtil;
import com.williamchung.project0.utils.PostgresConnectionUtil;

import java.util.Scanner;

public class BankAccountApplication extends Application{
    private UserService userService;
    private Scanner scanner;
    private Screen screen = null;
    private User user = null;

    //Constructors
    public BankAccountApplication(String title){
        this.title = title;
        this.scanner = new Scanner(System.in);
        screen = new LoginScreen();
    }
    public BankAccountApplication(){
        this("BankAccountApplication");
    }

    //run method
    @Override
    public void run() {
        String username = System.getenv("RDS_username");
        String password = System.getenv("RDS_password");
        ConnectionUtil connectionUtil = new PostgresConnectionUtil(
                "jdbc:postgresql://williamchung-training.ccys0ecscodk.us-east-2.rds.amazonaws.com:5432/postgres",
                username, password, "bankaccount");
        Repository<User, Integer> userRepository = new UserRepository(connectionUtil);
        this.userService = new UserService((UserRepository) userRepository);

        while(screen != null) {
            screen = screen.doScreen(this);
        }
    }

    //Getters & Setters
    public Scanner getScanner() {
        return scanner;
    }
    public UserService getUserService() {
        return userService;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
}
