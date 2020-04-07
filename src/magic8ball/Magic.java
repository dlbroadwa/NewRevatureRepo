package magic8ball;

import java.util.Random;

public class Magic {

	// procedural programming concepts
	// data types
	// "functions"
	// logic branches
	//loops
	
	String[] messages = new String[] { "All signs point to yes!", "Try again later", "nope"};
	
	// shake the ball
	// get message
	// print the message
	
	public static void main(String[] args) {
		Magic m = new Magic();
		
		String myMessage = m.shakeBall();
		
		m.printMessage(myMessage);
		

	}
	
	private String shakeBall() {
		return getMessage();
	}
	
	private int getMessageIndex() {
		Random r = new Random();
		return r.nextInt( (100- 0) +1) + 0;
	}
	
	private void printMessage(String msg) {
		System.out.println(msg);
	}
}
