package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(7, 1);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }
  String array[]={"Sunday","Saturday" ,"Monday", "Tuesday", "Wednesday","Thursday","Friday"};
  @Override
  public void run()
  {

    for (String s : array) {

//      System.out.println(s);

    }

//    for (int i=0 ; i<array.length ; i++){
//
//
//    }

    System.out.println(array[(magic8Ball.shake())].toUpperCase() +" is  a " + magic8Ball.shake() + " day of the week");
  }
}
