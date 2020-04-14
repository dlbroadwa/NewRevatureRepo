package com.proj;

import com.proj.app.Application;
import com.proj.app.SchedulingApplication;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Application app = new SchedulingApplication();


        app.run();






/*
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to the scheduling portal:");

        System.out.println("Enter Username: if you are an admin type username.admin: ");
        String user = scan.nextLine();

        System.out.println("Enter Password: ");
        String password = scan.nextLine();

        if (user.endsWith(".admin")) {
            System.out.println("Okay so you are an administrator... ");
            System.out.println("Pick a number: \n 1: Set Schedule \n 2: Check Schedule");

            int adminOptions = scan.nextInt();

            if (adminOptions == 1){
                System.out.println("You can now set up your class Schedule:");
            } else if (adminOptions == 2){
                System.out.println(("You can now see the students attending your class"));
            } else {
                System.out.println("Not A Valid input");
            }


        } else {
            System.out.println("Okay so your a user... \n Here are a list of classes you can choose from: ");

*/

        }
    }

