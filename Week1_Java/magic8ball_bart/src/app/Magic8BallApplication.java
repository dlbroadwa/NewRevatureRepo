package app;

// Concrete class
// Must implement application
public class Magic8BallApplication extends Application {
	private Magic8Ball magic8Ball;
	String[] messages = new String[]{"All signs point to yes!", "Maybe.", "Outlook not so good." , "Not likely at all.", "Ask again later."}; 
	
	public Magic8BallApplication() {
		magic8Ball = new Magic8Ball(messages.length,0);
	}
	
	public Magic8BallApplication(String title) {
		// Call the no-args constructor to setup the magic8ball without repeating code
		this();
		this.title = title;
	}
	
	@Override
	public void run() {
		int resultIndex = magic8Ball.shake();
		if (resultIndex >= messages.length) {
			resultIndex = messages.length - 1;
		}
		System.out.println(messages[resultIndex]);
	}
}
