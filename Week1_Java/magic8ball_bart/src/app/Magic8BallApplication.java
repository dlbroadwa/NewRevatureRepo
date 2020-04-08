package app;

import java.util.InputMismatchException;
import java.util.Scanner;

import screens.AnswerScreen;
import screens.QuestionScreen;
import screens.Screen;

// Concrete class
// Must implement application
public class Magic8BallApplication extends Application {
	private Magic8Ball magic8Ball;
	private Scanner scanner;
	private Screen currentScreen = null;
	//String[] messages = new String[]{"All signs point to yes!", "Maybe.", "Outlook not so good." , "Not likely at all.", "Ask again later."}; 
	
	public Magic8BallApplication() {
		magic8Ball = new Magic8Ball(3,0);
		//magic8Ball = new Magic8Ball(messages.length,0);
		this.scanner = new Scanner(System.in); // set our scanner to read input from the user
		currentScreen = new QuestionScreen();
	}
	
	public Magic8BallApplication(String title) {
		// Call the no-args constructor to setup the magic8ball without repeating code
		this();
		this.title = title;
	}
	
	@Override
	public void run() {
//		int resultIndex = magic8Ball.shake();
//		if (resultIndex >= messages.length) {
//			resultIndex = messages.length - 1;
//		}
//		System.out.println(messages[resultIndex]);
		
//		System.out.println("Ask the magic 8 ball anything\n>");
//		String input1 = scanner.nextLine();
//		System.out.println("You asked the 8 ball \n" + input1);
		
//		boolean gotNumber = false;
//		while (!gotNumber) {
//			try {
//				System.out.println("Give the magic 8 ball a number\n>");
//				int input2 = scanner.nextInt(); // Inputting anything else aside from a number leads to InputMismatchException
//				System.out.println("You gave the 8 ball \n" + input2);
//				gotNumber = true;
//			} catch (InputMismatchException ex) {
//				System.out.println("That's not a number!");
//				scanner.next();
//			}
//		}
//		scanner.close(); // DON'T DO THIS WHEN READING FROM SYSTEM.IN
//						 // Because scanner will close SYSTEM.IN in "a slow death" through menus
		
		while( currentScreen != null ) {
			currentScreen = currentScreen.doScreen(this);
		}
		
	}
	
	public Magic8Ball getMagic8Ball() {
	    return magic8Ball;
	}
	
	public Scanner getScanner() {
	    return scanner;
	}

}
