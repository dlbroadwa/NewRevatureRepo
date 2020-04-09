package com.company;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// Create a Scanner
        Scanner input = new Scanner(System.in);
    // Create login credentials
        String username;
        String password;
        int menu;


        System.out.println("Enter your username: ");
                username = input.nextLine();
         if (username.length() >= 0) {
             System.out.println("Enter your password:");
             password = input.nextLine();
             if (password.length() >=0) {
                 System.out.println("Would you like to begin a menu? If you would press 1, otherwise press 0: ");
                 menu = input.nextInt();
                 if (menu == 1) {
                     System.out.println("Perfect Lets Begin");
                 }
                    else if (menu == 0) {
                     System.out.println("Thanks for visiting our stock");
                 }

             }
        }



    }
}
