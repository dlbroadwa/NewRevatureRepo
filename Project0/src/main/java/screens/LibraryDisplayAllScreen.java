package screens;

import app.Application;
import app.LibraryApplication;
import models.Item;
import services.Catalog;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The LibraryDisplayAllScreen class serves as the container for the code of the Display ID List (Option 6)
 *    functionality for LibraryApplication's main menu.
 *
 *  <br> <br>
 *  Created: <br>
 *     21 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     21 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented doScreen method.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 21 April 2020
 */
public class LibraryDisplayAllScreen implements Screen {

    /*
     * Takes the currently running Application instance (assuming that it is LibraryApplication) and uses
     *   its Scanner and Catalog instance to allow a user to obtain a list of all entries from the list, condensed
     *   into entries with the IDNumber, Title and Author of each entry.
     * The Scanner serves the role of collecting user input for the Catalog to use as a parameter for its
     *   displayAll method, which is run here.
     * Note: While the Screen interface is expecting a Screen to be returned, null is returned instead as these
     *   actions will NOT lead to the running of another Screen subclass instance directly, as the user will be taken
     *   back to the main menu upon completion/abortion of the process.
     *
     * 	@param app Currently running LibraryApplication
     *
     * 	@return null null
     */
    public Screen doScreen(Application app) {
        Catalog c = ((LibraryApplication)app).getCatalog();

        if ( c.isCatalogEmpty() ) {
            System.err.println("Catalog is EMPTY!");
        } else {
            c.displayAll();
        }
        return null;
    }
}
