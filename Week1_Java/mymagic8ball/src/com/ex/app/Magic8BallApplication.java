package com.ex.app;


import com.ex.screens.QuestionScreen;

import com.ex.screens.Screen;
import com.ex.services.AnswerService;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;
  private Scanner scanner;
  private Screen currentScreen = null;
  private String currentQuestion = "";
  private AnswerService answerService = new AnswerService("resources/answers");



  public Magic8BallApplication() {
    int answerSize = answerService.answers.size()-1;
    magic8Ball = new Magic8Ball(answerSize, 0);
    this.scanner = new Scanner(System.in); // set our scanner to read input from the user
    currentScreen = new QuestionScreen();

  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {

    while(currentScreen != null){
      currentScreen = currentScreen.doScreen(this);
    }
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

  public Magic8Ball getMagic8Ball() {
    return magic8Ball;
  }

  public Scanner getScanner() {
    return scanner;
  }
}
