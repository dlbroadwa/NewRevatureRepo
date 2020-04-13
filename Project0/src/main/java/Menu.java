import book.Item;
import data.Catalog;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The Menu class serves as the main console of interaction for Users.
 *  Using a while loop menu setup, a User can enter integer and String values associated to various options and
 *    details to specify when searching for, checking, adding or removing Items from the Menu's Catalog.
 *  The Menu class takes the role of the Singleton class of the Singleton design pattern set up to not allow the
 *    existance of more than one Menu at a time when running the application.
 *
 *  <br> <br>
 *  Created: <br>
 *     11 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     11 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 10 April 2020
 */

public class Menu implements Runnable {
	// Instance Variables
	private static Menu uniqueInstance;

	// Constructor
	Menu() {}

	public static Menu getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Menu();
		}
		return uniqueInstance;
	}

	public void run() {
		// TODO Auto-generated method stub
		Catalog c = new Catalog();
		Scanner inputScanner = new Scanner(System.in);
		// Something for Admins as well?
		
		System.out.println("Welcome to the Library System!");
		boolean done = false;
		while (!done) {
			System.out.println("Choose an option: ");
			System.out.println("   1. Search for Item");
			System.out.println("   2. Check Out Item");
			System.out.println("   3. Check In Item");
			System.out.println("   4. Add New Item");
			System.out.println("   5. Remove Item");
			System.out.println("   6. Exit");

			// The if-else statements will allow a user to choose between the aforementioned choices by
			// inputting the corresponding integer when prompted to do so.
			// If the integer is valid, the program will proceed with the designated choice.
			// If not, then the program will display an error message and prompt the user to input a valid integer.
			try {
				int selection = inputScanner.nextInt();

				// Selection 1 - Search For Item
				if ( selection == 1 ) {
					// Similar to main menu, input must be a valid integer
					System.out.println("Please enter an ID # to search for: ");
					try {
						int searchIDInput = inputScanner.nextInt();
						Item result = c.searchByID(searchIDInput);
						if ( result != null ) {
							System.out.println("Item Found!");
							System.out.println("Information Below:");
							System.out.println(" ");
							result.printInfo();
							System.out.println(" ");
						} else {
							System.out.println("No Item with ID #" + searchIDInput + " found.");
						}
					} catch ( InputMismatchException ex) {
						System.err.println("ERROR: Non-Integer input detected. Returning to main menu.");
					}
				}

				// Selection 2 - Check Out Item
				if ( selection == 2 ) {
					// Similar to main menu, input must be a valid integer
					System.out.println("Please enter the ID # of the item that must be checked out: ");
					try {
						int searchIDInput = inputScanner.nextInt();
						Item result = c.searchByID(searchIDInput);
						if ( result.getCheckStatus() ) {
							System.out.println("Item Found!");
							System.out.println("Checking Out Item ID #" + result.getID());
							result.toggleCheckStatus();
							System.out.println("Done!");
							System.out.println(" ");
						} else if ( !result.getCheckStatus() ) {
							System.out.println("Item is found, but is already checked out.");
						} else if ( result == null ) {
							System.out.println("No Item with ID #" + searchIDInput + " found.");
						}
					} catch ( InputMismatchException e) {
						System.err.println("ERROR: Non-Integer input detected. Returning to main menu.");
					}
				}

				// Selection 3 - Check In Item
				if ( selection == 3 ) {
					// Similar to main menu, input must be a valid integer
					System.out.println("Please enter the ID # of the item that must be checked in: ");
					try {
						int searchIDInput = inputScanner.nextInt();
						Item result = c.searchByID(searchIDInput);
						if ( !result.getCheckStatus() ) {
							System.out.println("Item Found!");
							System.out.println("Checking In Item ID #" + result.getID());
							result.toggleCheckStatus();
							System.out.println("Done!");
							System.out.println(" ");
						} else if ( result.getCheckStatus() ) {
							System.out.println("Item is found, but is already checked in.");
						} else if ( result == null ) {
							System.out.println("No Item with ID #" + searchIDInput + " found.");
						}
					} catch ( InputMismatchException e) {
						System.err.println("ERROR: Non-Integer input detected. Returning to main menu.");
					}
				}

				// Selection 5 - Remove Item
				if ( selection == 5 ) {
					// Similar to main menu, input must be a valid integer
					System.out.println("Please enter the ID # of the item that must be removed: ");
					try {
						int searchIDInput = inputScanner.nextInt();
						Item result = c.searchByID(searchIDInput);
						if ( result != null ) {
							System.out.println("Item Found!");
							System.out.println("Removing Item.");
							c.removeBook(searchIDInput);
							System.out.println("Done.");
							System.out.println(" ");
						} else {
							System.out.println("No Item with ID #" + searchIDInput + " found.");
						}
					} catch ( InputMismatchException e) {
						System.err.println("ERROR: Non-Integer input detected. Returning to main menu.");
					}
				}

				// Selection 6 - Exit
				if ( selection == 6 ) {
					// End the while loop
					System.out.println("Exiting System. Thank you for your patronage!");
					done = true;
				}

				// Any other selection
				if ( selection < 1 || selection > 6 ) {
					// Do nothing
					System.out.println("Please enter a value between 1 and 6.");
				}

			} catch ( InputMismatchException ex) {
				System.err.println("ERROR: Non-Integer input detected. Please enter an Integer");
			}

		}
		
	}

}
