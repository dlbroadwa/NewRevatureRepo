package com.ex.app;


import com.ex.screens.QuestionScreen;
import com.ex.screens.Screen;
import com.ex.services.AnswerService;

//import javax.sound.midi.Soundbank;
//import java.util.InputMismatchException;
import java.util.Scanner;

// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  // Implement an AnswerScreen
  // that receives the question from the question screen and generate and displays and answer

  private  Magic8Ball magic8Ball;
  private Scanner scanner;
  private Screen currentScreen = null;
  private String currentQuestion = "";
  private AnswerService answerService = null;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball();
    this.scanner = new Scanner(System.in); // set our scanner to read input from the user
    currentScreen = new QuestionScreen();
    answerService = new AnswerService("resources/answers");
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {


      while(currentScreen != null) {
        currentScreen = currentScreen.doScreen(this);
      }



//    boolean gotNumber = false;
//    while(!gotNumber) {
//      try {
//        System.out.println("Give the 8 ball a number");
//        int input = scanner.nextInt();
//        System.out.println("You gave the 8 ball the number " + input);
//        gotNumber = true;
//      } catch (InputMismatchException ex)  {
//        System.out.println("That's not a number!");
//        scanner.next();
//      }
//    }
//    scanner.close(); // DON'T DO THIS WHEN READING FROM SYSTEM.IN because scanner will close SYSTEM.IN
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
}
