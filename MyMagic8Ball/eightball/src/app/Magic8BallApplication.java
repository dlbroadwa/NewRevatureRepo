package app;

public class Magic8BallApplication extends Application {

	  private  Magic8BBall magic8Ball;

	  public Magic8BallApplication() {
	  
		  magic8Ball = new Magic8BBall(9, 0);
	  
	  }

	  public Magic8BallApplication(String title) {
	    // call the no-args constructor to setup the magic8ball without repeating code
	    this();
	   
	    this.title = title;
	  
	  }

	  @Override
	  public void run() {
	    
		  String[] messages = new String[]{ "Signs point to yes", "Try asking again", 
				  "Outlook not good", "As I see it yes", "Cannot predict now", "Count on it", 
				  "Don't count on it", "Concentrate and ask again", "It is certian", "It is decidely so"};
		  
			  System.out.println(messages[magic8Ball.shake()]);
		  
	  }

}

