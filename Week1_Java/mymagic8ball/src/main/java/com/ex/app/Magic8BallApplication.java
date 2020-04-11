package com.ex.app;

import com.ex.config.Magic8BallConfig;
import com.ex.screens.QuestionScreen;
import com.ex.screens.Screen;
import com.ex.services.AnswerService;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Magic8BallApplication extends Application {

    private Magic8Ball magic8Ball;
    private Scanner scanner;
    private Screen currentScreen;
    private String currentQuestion = "";
    private AnswerService answerService;
    private Magic8BallConfig config;

    public Magic8BallApplication() {
        try {
            this.config = new Magic8BallConfig("config.txt");
            this.scanner = new Scanner(System.in);
            this.currentScreen = new QuestionScreen();
            this.answerService = new AnswerService(config.getAnswersPath());
            this.magic8Ball = new Magic8Ball(0, answerService.getNumAnswers() - 1);
        }
        catch (FileNotFoundException e) {
            System.err.println("ERROR: Could not find configuration file");
        }
    }
    public Magic8BallApplication(String title) {
        this();
        this.setTitle(title);
        this.scanner = new Scanner(System.in);
    }
    @Override
    public void run() {
        while (currentScreen != null) {
            currentScreen = currentScreen.doScreen(this);
        }
    }

    public Magic8Ball getMagic8Ball() {
        return magic8Ball;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(String currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public AnswerService getAnswerService() {
        return answerService;
    }

    public Magic8BallConfig getConfig() {
        return config;
    }
}
