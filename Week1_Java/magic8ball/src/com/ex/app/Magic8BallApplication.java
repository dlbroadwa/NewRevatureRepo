package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;
  private String[] messages = new String[]{"All signs point to yes", "Outlook not so good.", "Ask again later", "Cannot predict now"};

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
    System.out.println(messages[magic8Ball.shake()]);
  }
}
