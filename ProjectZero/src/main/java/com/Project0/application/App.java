package com.Project0.application;


import com.Project0.dao.UserDAO;
import com.Project0.dao.UserDAOImpl_FileIO;
import com.Project0.model.User;
import com.Project0.screens.Login;
import com.Project0.screens.Screen;

import java.io.IOException;
import java.util.Scanner;

public class App {
    //User Credentials cached for DAO login method
    private String username;
    private String password;
    //Object of USER after successful login
    private User user = null;

    private Scanner scanner;
    private Screen currentScreen = null;

    //Writer and Reader
    private UserDAO userDao = null;

    public App(){
        this.scanner = new Scanner(System.in);
        currentScreen = new Login();
    }

    public void run() {
        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

    public UserDAO getUserDao() {
        return new UserDAOImpl_FileIO();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void golferOptions(){
        Scanner in = new Scanner(System.in);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static void leagueOptions(){
        System.out.println("LEAGUE OPTIONS: \n");
    }

    public static void start() throws IOException, InterruptedException {

    }

}
