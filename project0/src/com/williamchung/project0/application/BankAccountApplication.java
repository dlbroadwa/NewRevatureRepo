package com.williamchung.project0.application;

import com.williamchung.project0.screens.LoginScreen;
import com.williamchung.project0.screens.Screen;

import java.sql.*;
import java.util.Scanner;

public class BankAccountApplication extends Application{
    private Connection connection;
    private Scanner scanner;
    private Screen screen = null;

    //Models to create? Am I biting off too much?
//    private User user = null;

    //Constructors
    public BankAccountApplication(String title){
        this.title = title;
        this.scanner = new Scanner(System.in);
        screen = new LoginScreen();
    }
    public BankAccountApplication(){
        this("BankAccountApplication");
    }

    @Override
    public void run() {
        System.out.println("Bank Application is starting");

        //MySQL connection (will separate to configurations package later)
        //Should I be doing this in Main, with Connection in the application constructor?
        String mySQLurl = "jdbc:mysql://localhost:3306";
        String mySQLuser = "root"; //Hey August
        String mySQLpass = "root"; //You'll probably need to tweak these lines
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(mySQLurl, mySQLuser, mySQLpass);
            System.out.println("MySQL connected successfully");
        } catch(Exception exception){
            System.out.println("MySQL failed to connect");
            System.out.println(exception);
        }

        //
        while(screen != null) {
            screen = screen.doScreen(this);
        }
    }

    //Getters & Setters

    public Scanner getScanner() {
        return scanner;
    }
}
