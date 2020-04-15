package com.ex;

import com.ex.app.Application;
import com.ex.app.Magic8BallApplication;
//import com.ex.app.OptimisticMagic8BallApplication;

//import java.util.Random;

public class Main {

  public static void main(String[] args) {
    // Polymorphism is the Application looking like some type of Application (Magic8BallApplication or
    // OptimisticMagic8BallApplication)
    Application app = new Magic8BallApplication();
    // ref type               object type

    // Abstraction is interfacing without knowing the type
    app.run();
  }























    // procedural programming concepts
    // data types
    // "functions"
    // logic branches
    // loops

//    String[] messages = new String[]{"All signs point to yes", "Outlook not so good.", "Ask again later"};
//
//    // shake the ball
//    // get message
//    // print the message
//    public static void main(String[] args) {
//      Main m = new Main();
//
//      String myMessage = m.shakeBall();
//
//      m.printMessage(myMessage);
//
//    }
//
//    private String shakeBall() {
//      int index = getMessageIndex();
//
//      if(index < 0) {
//        index = 0;
//      } else if(index >= messages.length) {
//        index = messages.length - 1;
//      }
//
//      return messages[index];
//    }
//
//    private int getMessageIndex() {
//      Random r = new Random();
//      return r.nextInt((messages.length - 0) + 1) + 0;
//    }
//
//    private void printMessage(String msg) {
//      System.out.println(msg);
//    }
}
