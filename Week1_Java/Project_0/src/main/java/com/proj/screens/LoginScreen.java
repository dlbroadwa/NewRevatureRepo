package com.proj.screens;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LoginScreen implements Screen {

    @Override
    public Screen doScreen(Application app) throws IOException {
        Scanner scanner = ((SchedulingApplication)app).getScanner();
        File userName = new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\nameAndSchedule");
        FileWriter fw = new FileWriter(userName, true);
        PrintWriter pw = new PrintWriter(fw);
        boolean found = false;
        String tempUsername = "";
        String tempPassword = "";

        System.out.println("WELCOME TO YOUR SATURDAY EVENT MANAGER: \n Enter Username: if you are an administrator type username.admin:");

        String user = scanner.nextLine();
        while (true) {

            if(user.length() == 0 || user.trim().equals("")) {
                continue;
            } else {
                pw.println(user);
            }
            pw.close();
            break;
        }

        System.out.println("Enter Password: ");
        String password = scanner.nextLine();

        try{
            Scanner scan = new Scanner(new File("C:\\Users\\johnn\\Desktop\\GitJump\\Project_0\\resources\\login"));
            scan.useDelimiter("[,\n]");

            while(scan.hasNext() && !found){
                tempUsername = scan.next();
                tempPassword = scan.next();

                if(tempUsername.trim().equals(user.trim()) && tempPassword.trim().equals(password.trim())){
                    found = true;
                }
            }
        }
        catch(Exception e){
            System.out.println("Wrong username");
        }


        if (user.endsWith(".admin") && found == true) {
            return (Screen) new AdminScreen();
        } else if(!user.endsWith(".admin") && found == true) {
            return (Screen) new UserScreen();
        } else {
            System.out.println("Wrong Password: try again");
        }
        return (Screen )new LoginScreen();
    }
}


/*
Scanner x;

        String username;
        String password;
        String filepath = "login.txt";

        public static void loginScreen(username, password, filepath) {

            System.out.println("Enter Username: if you are an administrator type username.admin: ");
            String user = scanner.nextLine();

            boolean found = false;
            String tempUsername = "";
            String tempPassword = "";

            try {
                x = new Scanner(new File(filepath));
                scanner.useDelimiter("[,\n]");

                while (x.hasNext() && !found) {
                    tempUsername = x.next();
                    tempPassword = x.next();

                    if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
                        found = true;
                    }
                }
            } catch (Exception e) {

            }
        }
 */