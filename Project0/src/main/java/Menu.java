import book.Dictionary;
import book.Item;
import book.Novel;
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
 *     										  Implemented code for selections 1, 2, 3, 5, and 6.
 * <br>
 *     12 April 2020, Barthelemy Martinon,    Added functionality to option 6 to update the Catalog file whenever
 *     											the user exits the system.
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
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 13 April 2020
 */

public class Menu implements Runnable {
	// Instance Variables
	private static Menu uniqueInstance;

	// Constructor
	private Menu() {}

	public static Menu getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new Menu();
		}
		return uniqueInstance;
	}

	private String trimLine(String inputString) {
		String trimmedString = inputString.replaceAll("\\s+", "");
		return trimmedString;
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
			System.out.println("   6. Save and Exit");
			System.out.println("   7. Exit without Saving");

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

				// Selection 4 - Add Item
				if ( selection == 4 ) {
					System.out.println("NOTICE: When inputting content regarding the new Item, remember that " +
							"whitespaces will be removed once input is added. Thank you for your understanding.");
					Item newItem = null;
					boolean doneAdding = false;
					while (!doneAdding) {
						try {
							// ID
							System.out.println("Please enter an ID # to be assigned to the new Item: ");
							int newId = inputScanner.nextInt();
							// Check if the ID number is already claimed
							if ( c.searchByID(newId) != null ) {
								System.err.println("ERROR: ID # already claimed. Please use a unique ID.");
								break;
							}
							inputScanner.nextLine();  // ensure that nextLine inputs are not skipped due to nextInt
							// Title
							System.out.println("Please enter the new Item's Title: ");
							// Trim Whitespaces from input string
							String newTitle = inputScanner.nextLine();
							newTitle = trimLine(newTitle);

							// Author
							System.out.println("Please enter the new Item's Author: ");
							String newAuthor = inputScanner.nextLine();
							// Trim Whitespaces from input string
							newAuthor = trimLine(newAuthor);

							// Publisher
							System.out.println("Please enter the new Item's Publisher: ");
							String newPublisher = inputScanner.nextLine();
							// Trim Whitespaces from input string
							newPublisher = trimLine(newPublisher);

							// Year
							System.out.println("Please enter the new Item's release Year: ");
							int newYear = inputScanner.nextInt();
							inputScanner.nextLine();

							// Subclass
							// Specify which subclass the new Item is an instance of.
							System.out.println("Please select Item type: ");
							System.out.println("   1. Dictionary");
							System.out.println("   2. Novel");
							// Selection 1 - Dictionary
							int subSelection = inputScanner.nextInt();
							inputScanner.nextLine();
							if ( subSelection == 1 ) {
								System.out.println("Confirmed as Dictionary.");
								// Language
								System.out.println("Please enter the new Dictionary's primary Language: ");
								String newLanguage = inputScanner.nextLine();
								// Trim Whitespaces from input string
								newLanguage = trimLine(newLanguage);

								// Word Count
								System.out.println("Please enter the new Dictionary's Word Count: ");
								int newWordCount = inputScanner.nextInt();
								// Check if value given isn't positive
								if ( newWordCount <= 0 ) {
									System.err.println("ERROR: You cannot have zero or negative defined words. " +
											"Please try again.");
									break;
								}
								inputScanner.nextLine();

								// Create new Dictionary and assign to newItem
								System.out.println("Information gathering complete! Creating new Dictionary!");
								newItem = new Dictionary(newId, newTitle, newAuthor, newPublisher, newYear,
										newLanguage, newWordCount);
								doneAdding = true;
							} else if ( subSelection == 2 ) {
								System.out.println("Confirmed as Novel.");
								// Genre
								System.out.println("Please enter the new Novel's Genre: ");
								String newGenre = inputScanner.nextLine();
								// Trim Whitespaces from input string
								newGenre = trimLine(newGenre);

								// Create new Novel and assign to newItem
								System.out.println("Information gathering complete! Creating new Novel!");
								newItem = new Novel(newId, newTitle, newAuthor, newPublisher, newYear,
										newGenre);
								doneAdding = true;
							} else {
								System.err.println("ERROR: Invalid choice entered. Please try again.");
							}
						} catch (InputMismatchException e) {
							System.err.println("ERROR: Input Mismatch detected. Please ensure that you are entering " +
									"valid values.");
						}
						if ( newItem != null ) {
							System.out.println("New Item added to Catalog!");
							c.addNewBook(newItem);
						}
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

				// Selection 6 - Save and Exit
				if ( selection == 6 ) {
					// Save changes made to the system
					c.updateCatalog();
					System.out.println("Catalog flushed and saved!");
					// End the while loop
					System.out.println("Exiting System. Thank you for your patronage!");
					done = true;
				}

				// Selection 7 - Exit without Saving
				if ( selection == 7 ) {
					// End the while loop
					System.out.println("Exiting System. No changes have been made to the Catalog. " +
							"Thank you for your patronage!");
					done = true;
				}

				// Any other selection
				if ( selection < 1 || selection > 7 ) {
					// Do nothing
					System.out.println("Please enter a value between 1 and 7.");
				}

			} catch ( InputMismatchException ex) {
				System.err.println("ERROR: Non-Integer input detected. Please enter an Integer");
			}

		}
		
	}

}
