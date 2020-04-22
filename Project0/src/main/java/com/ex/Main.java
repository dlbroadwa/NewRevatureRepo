package main.java.com.ex;

import main.java.com.ex.app.Application;
import main.java.com.ex.app.OnlineBankApplication;

/**
 * Main: this class holds the main method which only runs the application
 * 
 * methods
 * public static void main(String[] args): the main method
 * 
 * 
 * @author jtb12_000
 *
 */

public class Main {

	public static void main(String[] args) {
		Application app = new OnlineBankApplication();
		
		app.run();
	}
}
