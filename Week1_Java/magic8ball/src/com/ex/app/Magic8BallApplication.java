package com.ex.app;
import java.util.Scanner;

// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(4, 0);
    this.scanner = new Scanner(System.in);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
      }


  @Override
  public void run() {
//    //Created String array with messages
//    String[] messages = new String[]{"All signs point to yes", "Outlook not great so maybe stop?.", "I don't really have an opinion","Did you really ask that?","Maybe, Maybe Not. Are really caring what I say?"};
//   //Edited this line to use the shake number for the index of the array
//    System.out.println(messages[magic8Ball.shake()]);

    System.out.println("Ask the magic 8 ball anything");
    String input = scanner.nextLine();
    System.out.println("You asked the 8 ball \n" + input);
     }
}

 