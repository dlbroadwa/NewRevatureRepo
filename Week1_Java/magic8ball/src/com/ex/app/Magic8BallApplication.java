package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(3, 0);
  }
  public String[] ranString = {"Not Now; Not Never", "You got it dude", "nahhh"};


  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
    System.out.println(ranString[magic8Ball.shake()]);
  }
}
