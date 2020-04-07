import app.Application;
import app.Magic8BallApplication;
import app.OptimisticMagic8BallApplication;

//import java.util.Random;


public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application app = new Magic8BallApplication();
		app.run();
	}
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		// Polymorphism is the Application looking like a MagicBallApplication
//		Application app = new OptimisticMagic8BallApplication();
//		// ref type					object type
//		
//		// Abstraction is interfacing without knowing the type
//		app.run();
//	}

//	// procedural programming concepts
//	// data types
//	// "functions"
//	// logic branches
//	// loops
//	
//	String[] messages = new String[]{"All signs point to yes!", "Maybe.", "Outlook not so good." , "Not likely at all.", "Ask again later."}; 
//	
//	// Shake the ball
//	// Get message
//	// Print message
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		Main m = new Main();
//		String myMessage = m.shakeBall();
//		m.printMessage(myMessage);
//		
//	}
//	
//	private String shakeBall() {
//		int index = getMessageIndex();
//		
//		if (index < 0) {
//			index = 0;
//		} else if (index > messages.length) {
//			index = messages.length - 1;
//		}
//		return messages[index];
//	}
//	
//	private int getMessageIndex() {
//		Random r = new Random();
//		return r.nextInt(((messages.length - 1) - 0) + 1) + 0;
//	}
//	
//	private void printMessage(String msg) {
//		System.out.println(msg);
//	}

}
