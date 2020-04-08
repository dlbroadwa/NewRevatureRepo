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
  private AnswerService answerService = null;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(5, 0);
    this.scanner = new Scanner(System.in); // set our scanner to read input from the user
    currentScreen = new QuestionScreen();
    answerService = new AnswerService("resources/answers");
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }
  public String[] allAnswers = {"As I see it, yes."," Ask again later.","Better not tell you now.","Cannot predict now.","Concentrate and ask again.",
  "Don’t count on it.","It is certain."," It is decidedly so.","Most likely.","My reply is no.","My sources say no.","Outlook not so good.",
  "Outlook good.",  "Reply hazy, try again.", "Signs point to yes.","Very doubtful.","Without a doubt.","Yes.","Yes – definitely.","You may rely on it."};


  @Override
  public void run() {

    while(currentScreen != null){
      currentScreen = currentScreen.doScreen(this);
    }




//
//    int ansIndex =  magic8Ball.shake();
//    System.out.println("Ask a yes or no question:");
//    String input = scanner.nextLine();
//    System.out.println("You asked the 8 ball: \n" + input);
//    System.out.println(allAnswers[ansIndex]);
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
