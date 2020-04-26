package com.Project0.application;


import com.Project0.dao.*;
import com.Project0.model.Golfer;
import com.Project0.model.User;
import com.Project0.screens.Login;
import com.Project0.screens.Screen;
import com.Project0.services.GolferService;
import com.Project0.services.LeagueService;
import com.Project0.services.UserService;
import com.Project0.util.ConnectionUtil;
import com.Project0.util.PostgresConnectionUtil;
import com.Project0.util.UserPrefs;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    //User preferences on file
    UserPrefs prefs = null;

    //Writer and Reader
    //DAO DB objects
    ConnectionUtil connectionUtil = null;
    private UserService uService = null;
    private GolferService gService = null;
    private LeagueService lService = null;

    public App(){
        this.scanner = new Scanner(System.in);
        currentScreen = new Login();
        prefs = new UserPrefs();
        getConnectionUtil();
    }

    public void run() {
        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }
    }


    //Writer and Reader
    //DAO DB objects
    public UserService getuService() {
        if(uService == null)
            uService = new UserService(new UserDAOImpl_DB(getConnectionUtil()));
        return uService;
    }

    public ConnectionUtil getConnectionUtil() {
        if(connectionUtil == null) {
            connectionUtil = new PostgresConnectionUtil(prefs.getProperty("url"), prefs.getProperty("user"),
                    prefs.getProperty("password"), prefs.getProperty("schemaDefault"));
        }
        return connectionUtil;
    }

    public GolferService getgService() {
        if(gService == null)
            gService = new GolferService(new GolferDAOImpl_DB(getConnectionUtil()));
        return gService;
    }

    public LeagueService getlService() {
        if(lService == null)
            lService = new LeagueService(new LeagueDAO_ImplDB(getConnectionUtil()));
        return lService;
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
        //System.out.printf("CURRENT USER OBJECT: %s", getUser().toString());
        GolferService service = getgService();
        Golfer golfer = new Golfer();
        golfer.setName(getUser().getUsername());
//        System.out.printf("GOLFER NAME SET: %s", golfer.getName());

        ArrayList<Golfer> golfers;
        golfers = service.viewGolfer(golfer);

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