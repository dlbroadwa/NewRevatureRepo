package com.proj.app;
import com.proj.screens.LoginScreen;
import com.proj.screens.Screen;

import java.io.IOException;
import java.util.Scanner;


public class EventHandler {

    private Scanner scanner;
    private String username;
    private String password;

    //private User user = null;
    private Screen currentScreen = null;

    //private UserDAO userDao = null;

    public EventHandler() {
        this.scanner = new Scanner(System.in);
        currentScreen = new LoginScreen();
    }

    public void run() throws IOException {
        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
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
}
