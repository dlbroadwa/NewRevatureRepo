package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(4, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }


  @Override
  public void run() {
    String[] messages = new String[]{"All signs point to yes", "Outlook not great so maybe stop?.", "I don't really have an opinion","Did you really ask that?","Maybe, Maybe Not. Are really caring what I say?"};
   System.out.println(messages[magic8Ball.shake()]);
  }
}

