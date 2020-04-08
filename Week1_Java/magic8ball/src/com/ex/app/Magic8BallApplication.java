package com.ex.app;


import com.ex.screens.QuestionScreen;
import com.ex.screens.Screen;

import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;

// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {
  private static String[] messages = {"All signs point to yes", "Outlook not good", "Ask again later", "Most likely"};

  // Implement an AnswerScreen
  // that receives the question from the question screen and generate and displays and answer

  private  Magic8Ball magic8Ball;
  private Scanner scanner;
  private Screen currentScreen = null;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(messages.length-1, 0);
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
    while(currentScreen != null) {
      currentScreen = currentScreen.doScreen(this);
    }
  }

  public String getMessage() {
    int shakeResult = getMagic8Ball().shake();
    if (shakeResult < 0) shakeResult = 0;
    if (shakeResult >= messages.length) shakeResult = messages.length - 1;

    return messages[shakeResult];
  }

  public Magic8Ball getMagic8Ball() {
    return magic8Ball;
  }

  public Scanner getScanner() {
    return scanner;
  }
}
