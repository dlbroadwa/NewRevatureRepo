package com.proj.app;

import com.proj.screens.LoginScreen;
import com.proj.screens.Screen;

import java.util.Scanner;

public class SchedulingApplication extends Application {

    private Admin admin;
    private Scanner scanner;
    private Screen currentScreen;


    public SchedulingApplication(){
        this.scanner = new Scanner(System.in);
        currentScreen = new LoginScreen();

    }

    public SchedulingApplication(String title) {
        this();
        this.title = title;
    }



    @Override
    public void run() {

        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }

    }

    public Scanner getScanner() {return scanner; }


}
