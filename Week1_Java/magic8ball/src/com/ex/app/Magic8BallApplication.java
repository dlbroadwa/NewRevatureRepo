package com.ex.app;
import java.util.Scanner;

import com.ex.screens.QuestionScreen;
import com.ex.screens.Screen;

import javax.sound.midi.Soundbank;
import java.util.InputMismatchException;
import java.util.Scanner;

// Concrete class
// must impl Application
public class  Magic8BallApplication extends Application {

  // Implement an AnswerScreen
  // that receives the question from the question screen and generate and displays and answer

  private  Magic8Ball magic8Ball;
  private Scanner scanner;
<<<<<<< HEAD

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(4, 0);
    this.scanner = new Scanner(System.in);
=======
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
//    //Created String array with messages
//    String[] messages = new String[]{"All signs point to yes", "Outlook not great so maybe stop?.", "I don't really have an opinion","Did you really ask that?","Maybe, Maybe Not. Are really caring what I say?"};
//   //Edited this line to use the shake number for the index of the array
//    System.out.println(messages[magic8Ball.shake()]);

    System.out.println("Ask the magic 8 ball anything");
    String input = scanner.nextLine();
    System.out.println("You asked the 8 ball \n" + input);
     }
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
  }
>>>>>>> 69a2117bfa2b7bf2561564eb40a86ca3eadeae83
}

 