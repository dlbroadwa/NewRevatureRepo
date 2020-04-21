package app;

import connections.ConnectionUtil;
import connections.PostgresConnectionUtil;
import data.DAO;
import data.SqlDAO;
import models.Item;
import repos.ItemSQLRepository;
import repos.Repository;
import services.Catalog;
import screens.*;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The LibraryApplication class serves as the main console of interaction for Users.
 *  Using a while loop menu setup, a User can enter integer and String values associated to various options and
 *    details to specify when searching for, checking, adding or removing Items from the app.Menu's Catalog.
 *  The app.Menu class takes the role of the Singleton class of the Singleton design pattern set up to not allow the
 *    existance of more than one app.Menu at a time when running the application.
 *
 *  <br> <br>
 *  Created: <br>
 *     11 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     11 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented code for selections 1, 2, 3, 5, and 6.
 * <br>
 *     12 April 2020, Barthelemy Martinon,    Added functionality to option 6 to update the Catalog file whenever
 *     											the unused.user exits the system.
 *     										  Began implementation on option 4.
 * <br>
 *     13 April 2020, Barthelemy Martinon,    Updated code for option 4 to trim out any whitespaces found in input
 *     											to ensure that the input is structured to function with the FileIO
 *     										    setup found in Catalog.
 *     										    Deviation from original idea of rejecting input with whitespaces, could
 *     										    have required nested conditions and try/catch blocks that would have
 *     										    complicated code further.
 *     										  Implemented trimSpaces method.
 * <br>
 *     16 April 2020, Barthelemy Martinon,    Catalog instance creation now requires a username and password to serve
 *     											as parameters for SqlDAO instance creation creation through new Catalog
 *     										    constructor.
 *     										  Rework all options to call updateCatalog so that any change made to the
 *     											catalog content is immediately done when taking any actions.
 *     										  Option 6 no longer saves when performed, application is simply shut down.
 * <br>
 *     18 April 2020, Barthelemy Martinon,    Modularized and moved all option functionality to their respective Screen
 *     											subclass code to abstract functionality code and simply/reduce the size
 *     											of LibraryApplication.
 *     										  LibraryApplication (formerly Menu) now extends Application.
 * <br>
 *     21 April 2020, Barthelemy Martinon,    Added new option for displaying all entries in the system in single-lined
 *     											condensed console statements. Takes on the slot of Option 6.
 *     										  Exit option reworked to be Option 0 to avoid having to renumber options
 *     										  	every time a new option is added.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 21 April 2020
 */

public class LibraryApplication extends Application implements Runnable {
	// Instance Variables
	private static LibraryApplication uniqueInstance;
	private Catalog c;
	private Scanner inputScanner;
	private Screen currentScreen = null;

	// Constructor
	private LibraryApplication() {
		// Security layer through run environment variables
		String url = System.getenv("ENV_VAR_P0_POSTGRESQL_DB_URL");
		String username = System.getenv("ENV_VAR_P0_ADMIN_USERNAME");
		String password = System.getenv("ENV_VAR_P0_ADMIN_PASSWORD");
		String defaultSchema = System.getenv("ENV_VAR_P0_POSTGRESQL_DB_DEFAULT_SCHEMA");
		// Theoretically, this is where user authentication would go.

		// Initialize Dependencies
		ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
		Repository<Item,Integer> repo = new ItemSQLRepository(connectionUtil);
		DAO dao = new SqlDAO(repo);

		// Initialize Catalog and Scanner
		c = new Catalog(dao);
		inputScanner = new Scanner(System.in);
	}

	// Getter Methods

	public static LibraryApplication getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new LibraryApplication();
		}
		return uniqueInstance;
	}

	public Catalog getCatalog() {
		return c;
	}

	public Scanner getScanner() {
		return inputScanner;
	}

	// Methods

	public String trimLine(String inputString) {
		return inputString.replaceAll("\\s+", "");
	}

	public void run() {
		
		System.out.println("Welcome to the Library System!");
		boolean done = false;
		while (!done) {
			System.out.println("Choose an option: ");
			System.out.println("   1. Search for Item");
			System.out.println("   2. Check Out Item");
			System.out.println("   3. Check In Item");
			System.out.println("   4. Add New Item");
			System.out.println("   5. Remove Item");
			System.out.println("   6. Display ID List");
			System.out.println(" ");
			System.out.println("   0. Exit");

			// The if-else statements will allow a unused.user to choose between the aforementioned choices by
			// inputting the corresponding integer when prompted to do so.
			// If the integer is valid, the program will proceed with the designated choice.
			// If not, then the program will display an error message and prompt the unused.user to input a valid integer.
			try {
				int selection = inputScanner.nextInt();

				// Selection 1 - Search For Item
				if ( selection == 1 ) {
					currentScreen = new LibrarySearchScreen();
					currentScreen.doScreen(this);
				}

				// Selection 2 - Check Out Item
				if ( selection == 2 ) {
					currentScreen = new LibraryCheckOutScreen();
					currentScreen.doScreen(this);
				}

				// Selection 3 - Check In Item
				if ( selection == 3 ) {
					currentScreen = new LibraryCheckInScreen();
					currentScreen.doScreen(this);
				}

				// Selection 4 - Add Item
				if ( selection == 4 ) {
					currentScreen = new LibraryAddScreen();
					currentScreen.doScreen(this);
				}

				// Selection 5 - Remove Item
				if ( selection == 5 ) {
					currentScreen = new LibraryRemoveScreen();
					currentScreen.doScreen(this);
				}

				// Selection 6 - Display ID List
				if ( selection == 6 ) {
					currentScreen = new LibraryDisplayAllScreen();
					currentScreen.doScreen(this);
				}

				// Selection 0 - Exit
				if ( selection == 0 ) {
					// End the while loop, program ends
					System.out.println("Exiting System. Thank you for your patronage!");
					done = true;
				}

				// Any other selection
				if ( selection < 0 || selection > 6 ) {
					// Do nothing
					System.out.println("Please enter a value between 0 and 6.");
				}

			} catch ( InputMismatchException ex) {
				System.err.println("ERROR: Non-Integer input detected. Please enter an Integer");
				inputScanner.next(); // clear up the erroneous entry
			}

			currentScreen = null; // reset the screen to null
			// end of while loop, repeat back to top if boolean is not toggled
		}
	}
}
