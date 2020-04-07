package com.ex.app;


// Concrete class
// must impl Application
public class Magic8BallApplication extends Application
{

  private  Magic8Ball magic8Ball;

  public Magic8BallApplication()
  {
    magic8Ball = new Magic8Ball(8, 0);
  }

  public Magic8BallApplication(String title) {
    // call the no-args constructor to setup the magic8ball without repeating code
    this();
    this.title = title;
  }

  @Override
  public void run()
  {
    int solve = magic8Ball.shake();
    switch (solve)
    {
      case 0: System.out.println("Ask again Later.");
            break;
      case 1: System.out.println("Better not tell you now.");
            break;
      case 2: System.out.println("Cannot Predict now.");
            break;
      case 3: System.out.println("Concentrate and ask again.");
        break;
      case 4: System.out.println("Don't count on it.");
        break;
      case 5: System.out.println("It is Certain");
        break;
      case 6: System.out.println("It is most likely so.");
        break;
      case 7: System.out.println("Hard pass");
        break;
      case 8: System.out.println("Absolutely");
        break;

    }
  }


}
