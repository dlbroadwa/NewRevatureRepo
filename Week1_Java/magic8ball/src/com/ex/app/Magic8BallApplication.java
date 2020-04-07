package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  private String[] responses= {"Very Grim!", "Not looking so good.", "Very good luck!", "Better luck next time!"};

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(responses.length - 1, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
    System.out.println(responses[magic8Ball.shake()]);
  }
}
