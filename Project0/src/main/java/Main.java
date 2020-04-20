import app.LibraryApplication;

/**
 *  Project 0:<br>
 * <br>
 *  The Main class controls the initialization and test run of the application as a whole.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.                                        
 * <br>
 *     08 April 2020, Barthelemy Martinon,    Began using class to perform rudimentary unit 
 *     											tests on Books and Catalog, and their methods
 * <br>
 *     09 April 2020, Barthelemy Martinon,    Began using class to perform rudimentary unit 
 *     											tests on Catalog's FileIO system
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 09 April 2020
 */

public class Main {

	public Main() {
		//app.Menu m = new app.Menu();
		LibraryApplication m = LibraryApplication.getInstance();
		m.run();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();
	}
}
