package main.java.com.ex.screens;

import main.java.com.ex.app.Application;

/**
 * Screen: interface that allows for display to the console
 * 
 * methods
 * Screen display(Application app): a method that displays to the console and accepts an Application as input for access to
 * data and services 
 * 
 * 
 * @author jtb12_000
 *
 */

public interface Screen {
	Screen display(Application app);
}
