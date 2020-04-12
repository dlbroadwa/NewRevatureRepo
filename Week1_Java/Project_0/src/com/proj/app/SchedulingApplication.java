package com.proj.app;

import com.proj.screens.LoginScreen;
import com.proj.screens.Screen;
import com.proj.services.AnswerService;

import java.io.IOException;
import java.util.Scanner;

public class SchedulingApplication extends Application {

    private Scanner scanner;
    private Screen currentScreen;
    private AnswerService answerServices = null;



    public SchedulingApplication(){
        this.scanner = new Scanner(System.in);
        currentScreen = new LoginScreen();



    }

    public SchedulingApplication(String title) {
        this();
        this.title = title;
    }



    @Override
    public void run() throws IOException {

        while(currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }

    }

    public Scanner getScanner() {return scanner; }


}
