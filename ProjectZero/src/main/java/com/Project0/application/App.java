package com.Project0.application;


import com.Project0.dao.*;
import com.Project0.model.Golfer;
import com.Project0.model.User;
import com.Project0.screens.Login;
import com.Project0.screens.Screen;
import com.Project0.util.ConnectionUtil;
import com.Project0.util.PostgresConnectionUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
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
    //DAO DB objects
    ConnectionUtil connectionUtil = null;
    private UserDAO userDao = null;

    public App(){
        this.scanner = new Scanner(System.in);
        currentScreen = new Login();
        getConnectionUtil();
    }

    public void run() {
        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }
    }


    //Writer and Reader
    //DAO DB objects
    public UserDAO getUserDao() {
        return new UserDAOImpl_DB(getConnectionUtil());
    }
    public ConnectionUtil getConnectionUtil() {
        if(connectionUtil == null) {
            connectionUtil = new PostgresConnectionUtil(
                    "jdbc:postgresql://database-1.cdr3hmlqxdcv.us-east-2.rds.amazonaws.com:5432/project0",
                    "postgres", "Cavalier93!", "public");
        }
        return connectionUtil;
    }

    //g&S
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
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public String getUserAccessLevel(User user) { return this.user.getAccessLevel(); }
    public Golfer getGolferFromLoggedInUser() {
        //System.out.printf("CURRENT USER OBJECT: %s", app.getUser().toString());
        GolferDAO dao = new GolferDAOImpl_FileIO();
        Golfer golfer = new Golfer();
        golfer.setName(this.getUser().getUsername());

        ArrayList<Golfer> golfers = new ArrayList<>();
        golfers = dao.viewGolferInfo(golfer);

//        for(Golfer e : golfers)
//            System.out.printf("GOLFERS RETRIEVED: %s", e.toString());
        //handle array size - SHOULD ONLY BE 1 retrieved from login UserObject
        if(golfers.size() > 1) {
            System.out.println("PROBLEM WITH RETRIEVAL - MORE THAN ONE FOUND");
            return null;
        }
        else if (golfers.size() <= 0) {
            System.out.println("PROBLEM WITH RETRIEVAL - NONE FOUND");
            return null;
        }
        else {
            golfer = golfers.get(0);
            return golfer;
        }
    }
    public Scanner getScanner() {
        return scanner;
    }

    //hash password values
    public static String generateHash(String inPassword) {
        StringBuilder hash = new StringBuilder();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = sha.digest(inPassword.getBytes());
            char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                    'a', 'b', 'c', 'd', 'e', 'f' };
            for(int itr = 0; itr < hashedBytes.length; itr++) {
                byte b = hashedBytes[itr];
                hash.append(digits[(b & 0xf0) >> 4]);
                hash.append(digits[b & 0x0f]);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash.toString();
    }
}
