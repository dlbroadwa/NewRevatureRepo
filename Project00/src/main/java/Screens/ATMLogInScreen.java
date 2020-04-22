package Screens;

import java.util.Scanner;

public class ATMLogInScreen {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    private String email;
    private String pin;

    public ATMLogInScreen(){
        this.email = null;
        this.pin = null;
    }
    public void printFirstScreen(){
        System.out.println("Welcome to Bank Of America ATM Service:");
        System.out.print("Enter your username (email address) to proceed: ");
        Scanner sc = new Scanner(System.in);
        this.setEmail(sc.next());
        System.out.print("Enter your pin: ");
        this.setPin(sc.next());
    }
}
