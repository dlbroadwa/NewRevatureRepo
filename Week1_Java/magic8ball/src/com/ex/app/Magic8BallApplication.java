package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(3, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
    String[] strArray= new String[4];
    strArray[0] = "Hello 1";
    strArray[1] = "Hello 2";
    strArray[2] = "Hello 3";
    strArray[3] = "Hello 4";
    System.out.println(strArray[magic8Ball.shake()]);

  }
}
