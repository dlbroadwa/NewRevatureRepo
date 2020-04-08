package com.ex.app;


<<<<<<< HEAD
=======
import com.ex.screens.QuestionScreen;
import com.ex.screens.Screen;

import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;

>>>>>>> 69a2117bfa2b7bf2561564eb40a86ca3eadeae83
// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

<<<<<<< HEAD
  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(3, 0);
=======
  // Implement an AnswerScreen
  // that receives the question from the question screen and generate and displays and answer

  private  Magic8Ball magic8Ball;
  private Scanner scanner;
  private Screen currentScreen = null;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(3, 0);
    this.scanner = new Scanner(System.in); // set our scanner to read input from the user
    currentScreen = new QuestionScreen();
>>>>>>> 69a2117bfa2b7bf2561564eb40a86ca3eadeae83
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
<<<<<<< HEAD
    String[] strArray= new String[4];
    strArray[0] = "Hello 1";
    strArray[1] = "Hello 2";
    strArray[2] = "Hello 3";
    strArray[3] = "Hello 4";
    System.out.println(strArray[magic8Ball.shake()]);

=======


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
>>>>>>> 69a2117bfa2b7bf2561564eb40a86ca3eadeae83
  }
}
