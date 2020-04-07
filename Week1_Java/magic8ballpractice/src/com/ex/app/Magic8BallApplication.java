package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(8, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run() {
    switch(magic8Ball.shake()) {
      case 1:
        System.out.println("As I see it, yes.\n");
        break;
      case 2:
        System.out.println("Better not tell you now.\n");
        break;
      case 3:
        System.out.println("Cannot predict now.\n");
        break;
      case 4:
        System.out.println("Concentrate and ask again.");
        break;
      case 5:
        System.out.println("It is certain.\n");
        break;
      case 6:
        System.out.println("My sources say no.\n");
        break;
        case 7:
        System.out.println("Very doubtful.\n");
        break;
      default: System.out.println("Ask again later.");
    }




  }
}
