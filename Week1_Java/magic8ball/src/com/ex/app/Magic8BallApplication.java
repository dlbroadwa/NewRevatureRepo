package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private static String[] messages = {"All signs point to yes", "Outlook not good", "Ask again later", "Most likely"};
  private  Magic8Ball magic8Ball;

  private String getMessage() {
      int shakeResult = magic8Ball.shake();
      if (shakeResult < 0) shakeResult = 0;
      if (shakeResult >= messages.length) shakeResult = messages.length - 1;

      return messages[shakeResult];
  }
  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(messages.length-1, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
    System.out.println(getMessage());
  }
}
