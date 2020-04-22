package Screens;

import model.Accounts;
import model.Users;

import java.util.Scanner;

public class ATMScreenMenu {

    public int getUserMenuOption() {
        return userMenuOption;
    }

    public void setUserMenuOption(int userMenuOption) {
        this.userMenuOption = userMenuOption;
    }

    private int userMenuOption;

    public ATMScreenMenu (){
        this.userMenuOption = -1;
    }

    public void printATMMenuScreen(Users user){
        System.out.println("\n:::::::::::::::::::::::::::::::::::::::");
        System.out.println("Welcome to Bank Of America ATM Service:");
        System.out.println(user.getEmail_address() + " is logged in");
        System.out.println("------User detail------");
        System.out.println("User Name: " + user.getName() + ".------User Email: " + user.getEmail_address() + ".------User Phone number: " + user.getPhone_number() + ".");
    }

    public void printUserAccount(Accounts account){
        System.out.println("Account holder: " + account.getHolder().getEmail_address() + ".------Balance: $" +
                account.getBalance() + ".------Account Type: " + account.getAccountType() + ".------Account ID: " + account.getAccount_id());
    }

    private void printATMOptions(){
        System.out.println("{1}  Withdraw");
        System.out.println("{2}  Deposit");
        System.out.println("{3}  See past transactions");
        System.out.println("{4}  Exit");
    }
    public void printUserOptions(){
        printATMOptions();
        System.out.print("Please enter your choice: ");
        int number;
        int count = 0;
        Scanner sc = new Scanner(System.in);

        do {

            while (!sc.hasNextInt()) {
                System.out.println("That is not a number.!");
                sc.next();
                printATMOptions();
                System.out.print("Please enter your choice: ");
                System.out.println();
                count++;
                if (count > 4 ){
                    break;
                }
            }

            if (count > 4){
                this.userMenuOption = 4;
                System.out.println("Incorrect input " + count + " times.  Exiting program.");
                return;
            }
            count++;
            number = sc.nextInt();
            if (number == 1 || number == 2 || number == 3 || number == 4 ) {
                break;
            }

            printATMOptions();
            System.out.println("Please enter choice from 1 to 5: ");
        } while (number != 1 || number != 2 || number != 3 || number != 4);

        this.userMenuOption = number;
    }
}
