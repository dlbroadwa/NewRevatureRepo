package Screens;

import model.Users;

import java.util.Scanner;

public class ManagerMenu {

    private int managerMenuOption;

    public ManagerMenu(){
        this.managerMenuOption = 0;
    }

    public int getManagerMenuOption() {
        return managerMenuOption;
    }

    private void printMenu(Users user){
        System.out.println("------Manager Menu Options------");
        System.out.println("{1} View All Users");

        System.out.println("{2} View Account by User ID (Email)");
        System.out.println("{3} Create User");
        System.out.println("{4} Create Account");
        System.out.println("{5} Reset Password (8 characters max)");
        System.out.println("{6} View All Accounts");
        System.out.println("{7} Exit");

    }

    public void printManagerMenu(Users user){
        printMenu(user);
        System.out.print("Please enter your choice: ");
        int number;
        int count = 0;
        Scanner sc = new Scanner(System.in);

        do {

            while (!sc.hasNextInt()) {
                System.out.println("That is not a number!");
                sc.next();
                System.out.println(user.getEmail_address() + " is a bank manager.");
                printMenu(user);
                System.out.print("Please enter your choice: ");
                System.out.println();
                count++;
                if (count > 4 ){
                    break;
                }
            }

            if (count > 4){
                this.managerMenuOption = 4;
                System.out.println("Incorrect input " + count + " times.  Exiting program.");
                return;
            }
            count++;
            number = sc.nextInt();

            if (number == 1 || number == 2 || number == 3 || number == 4  || number == 5 || number == 6 || number == 7 ) {

                break;
            }
            System.out.println(user.getEmail_address() + " is a bank manager.");
            printMenu(user);
            System.out.println("Please enter choice from 1 to 7:");
        } while (number != 1 || number != 2 || number != 3 || number != 4 || number != 5 || number != 6 || number != 7 );

        this.managerMenuOption = number;
    }

}
