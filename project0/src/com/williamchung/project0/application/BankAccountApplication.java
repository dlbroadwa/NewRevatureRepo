package com.williamchung.project0.application;

import com.williamchung.project0.screens.Screen;

import java.util.Scanner;

public class BankAccountApplication extends Application{

    private Scanner scanner;
    private Screen screen = null;

    public BankAccountApplication(){}
    public BankAccountApplication(String title){
        this();
        this.title = title;
    }


    @Override
    public void run() {
        System.out.println("Bank Application is running");
        while(screen != null) {
            screen = screen.doScreen(this);
        }
    }

    //Getters & Setters

    public Scanner getScanner() {
        return scanner;
    }
}
