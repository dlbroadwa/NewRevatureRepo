package screens;

import app.Application;
import app.LibraryApplication;
import models.Dictionary;
import models.Item;
import models.Novel;
import services.Catalog;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The LibraryAddScreen class serves as the container for the code of the Add New Item (Option 4) functionality
 *    for LibraryApplication's main menu.
 *
 *  <br> <br>
 *  Created: <br>
 *     18 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     18 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented doScreen method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 18 April 2020
 */
public class LibraryAddScreen implements Screen {

    /*
     * Takes the currently running Application instance (assuming that it is LibraryApplication) and uses
     *   its Scanner and Catalog instance to allow a user to add a new Item into the Catalog with specified input used
     *   to fill out the various data values it should hold.
     * The Scanner serves the role of collecting user input for the Catalog to use as a parameter for its
     *   addNewBook method, which is run here.
     * Note: While the Screen interface is expecting a Screen to be returned, null is returned instead as these
     *   actions will NOT lead to the running of another Screen subclass instance directly, as the user will be taken
     *   back to the main menu upon completion/abortion of the process.
     *
     * 	@param app Currently running LibraryApplication
     *
     * 	@return null null
     */
    public Screen doScreen(Application app) {
        Scanner scanner = ((LibraryApplication)app).getScanner();
        Catalog c = ((LibraryApplication)app).getCatalog();

        System.out.println("NOTICE: When inputting content regarding the new Item, remember that " +
                "whitespaces will be removed once input is added. Thank you for your understanding.");
        Item newItem = null;
        boolean doneAdding = false;
        while (!doneAdding) {
            try {
                // ID
                System.out.println("Please enter an ID # to be assigned to the new Item: ");
                int newId = scanner.nextInt();
                // Check if the ID number is already claimed
                if ( c.searchByID(newId) != null ) {
                    System.err.println("ERROR: ID # already claimed. Please use a unique ID.");
                    break;
                }
                scanner.nextLine();  // ensure that nextLine inputs are not skipped due to nextInt
                // Title
                System.out.println("Please enter the new Item's Title: ");
                // Trim Whitespaces from input string
                String newTitle = scanner.nextLine();
                newTitle = ((LibraryApplication)app).trimLine(newTitle);

                // Author
                System.out.println("Please enter the new Item's Author: ");
                String newAuthor = scanner.nextLine();
                // Trim Whitespaces from input string
                newAuthor = ((LibraryApplication)app).trimLine(newAuthor);

                // Publisher
                System.out.println("Please enter the new Item's Publisher: ");
                String newPublisher = scanner.nextLine();
                // Trim Whitespaces from input string
                newPublisher = ((LibraryApplication)app).trimLine(newPublisher);

                // Year
                System.out.println("Please enter the new Item's release Year: ");
                int newYear = scanner.nextInt();
                scanner.nextLine();

                // Subclass
                // Specify which subclass the new Item is an instance of.
                System.out.println("Please select Item type: ");
                System.out.println("   1. Dictionary");
                System.out.println("   2. Novel");
                // Selection 1 - Dictionary
                int subSelection = scanner.nextInt();
                scanner.nextLine();
                if ( subSelection == 1 ) {
                    System.out.println("Confirmed as Dictionary.");
                    // Language
                    System.out.println("Please enter the new Dictionary's primary Language: ");
                    String newLanguage = scanner.nextLine();
                    // Trim Whitespaces from input string
                    newLanguage = ((LibraryApplication)app).trimLine(newLanguage);

                    // Word Count
                    System.out.println("Please enter the new Dictionary's Word Count: ");
                    int newWordCount = scanner.nextInt();
                    // Check if value given isn't positive
                    if ( newWordCount <= 0 ) {
                        System.err.println("ERROR: You cannot have zero or negative defined words. " +
                                "Please try again.");
                        break;
                    }
                    scanner.nextLine();

                    // Create new Dictionary and assign to newItem
                    System.out.println("Information gathering complete! Creating new Dictionary!");
                    newItem = new Dictionary(newId, true, newTitle, newAuthor, newPublisher,
                            newYear, newLanguage, newWordCount);
                    doneAdding = true;
                } else if ( subSelection == 2 ) {
                    System.out.println("Confirmed as Novel.");
                    // Genre
                    System.out.println("Please enter the new Novel's Genre: ");
                    String newGenre = scanner.nextLine();
                    // Trim Whitespaces from input string
                    newGenre = ((LibraryApplication)app).trimLine(newGenre);

                    // Create new Novel and assign to newItem
                    System.out.println("Information gathering complete! Creating new Novel!");
                    newItem = new Novel(newId, true, newTitle, newAuthor, newPublisher, newYear,
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
        return null;
    }
}
