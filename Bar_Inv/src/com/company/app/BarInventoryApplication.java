package com.company.app;

import com.company.screens.Menu;
import com.company.screens.Screen;

import java.util.Scanner;

public class BarInventoryApplication extends Application{
    private Screen currentScreen = null;
    private Scanner scanner;



    public BarInventoryApplication(){
        this.scanner = new Scanner(System.in); // set our scanner to read input from the user
        currentScreen = new Menu();

    }

    public BarInventoryApplication(String title){
        this();
        this.title=title;
    }


    @Override
    public void run() {
        while (currentScreen != null){
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Scanner getScanner() {
        return scanner;
    }

}
