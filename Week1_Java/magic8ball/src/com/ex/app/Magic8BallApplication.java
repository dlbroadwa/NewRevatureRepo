package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application {

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication() {
    magic8Ball = new Magic8Ball(7, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run()
  {
    switch(magic8Ball.shake()){
      case 1:
        System.out.println("Yes");
        break;
      case 2:
        System.out.println("No");
        break;
      case 3:
        System.out.println("Maybe");
        break;
      case 4:
        System.out.println("Never");
        break;
      case 5:
        System.out.println("It doesn't matter");
        break;
      case 6:
        System.out.println("YOLO");
        break;
      case 7:
        System.out.println("Ask someone who cares");
        break;
      default:
        System.out.println("Ask again later");
    }
  }
}
