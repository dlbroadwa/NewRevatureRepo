package screens;

import app.Application;
import app.LibraryApplication;
import models.Catalog;
import models.Item;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The LibrarySearchScreen class serves as the container for the code of the Search For Item (Option 1) functionality
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
public class LibrarySearchScreen implements Screen {

    /*
     * Takes the currently running Application instance (assuming that it is LibraryApplication) and uses
     *   its Scanner and Catalog instance to allow a user to search for an item by its ID.
     * The Scanner serves the role of collecting user input for the Catalog to use as a parameter for its
     *   serachByID method, which is run here.
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

        // Similar to main menu, input must be a valid integer
        System.out.println("Please enter an ID # to search for: ");
        try {
            int searchIDInput = scanner.nextInt();
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
        return null;
    }
}
