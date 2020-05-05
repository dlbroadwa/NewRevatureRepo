package com.Screens;

import com.model.Users;

import java.util.Scanner;

public class CreateAccountMenu {
    private float initalBalance;
    private String type;
    private Users customer;
    public CreateAccountMenu (){
        this.initalBalance = 0;
        this.type = "checking";
        this.customer = new Users();
    }

    public void askAccountDetails(){
        System.out.println("Enter user email as account holder: ");
        Scanner sc = new Scanner(System.in);
        String email = sc.next();
        this.customer.setEmail_address(email);

        askInitialBalance();
        askAccountTye();

    }

    private void askAccountTye() {
        Scanner sc = new Scanner(System.in);
        String type ;
        int count = 0;
        do{
            System.out.println("Enter Account type ('checking' or 'saving')");
            type = sc.next();
            type = type.toLowerCase();
            if (type.equals("checking") || type.equals("saving")){
                this.type = type;
                return;
            }
            else{
                count++;
                if (count >=5){
                    System.out.println("incorrect account type entered " + count + " times.  Default to checking");
                    this.type = "checking";
                    return;
                }
            }
        }while (true);
    }

    private void askInitialBalance() {
        Scanner sc = new Scanner(System.in);
        float amount;
        System.out.println("Enter initial deposit amount: ");
        int count = 0;

        while (!sc.hasNextFloat()){
            System.out.println("That is not a proper number");
            count++;
            if (count > 4){
                System.out.println("Incorrect amount enter " + count + " times \nReturning to Menu.");
                return;
            }
            sc.next();
            System.out.println("Enter deposit amount: ");
        }

        this.initalBalance = sc.nextFloat();

        if (this.initalBalance < 0){
            this.initalBalance = this.initalBalance * -1 ;
        }
    }

    public float getInitalBalance() {
        return initalBalance;
    }

    public void setInitalBalance(float initalBalance) {
        this.initalBalance = initalBalance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Users getCustomer() {
        return customer;
    }

    public void setCustomer(Users customer) {
        this.customer = customer;
    }
}
