package app;

import connections.ConnectionUtil;
import connections.PostgresConnectionUtil;
import models.Pet;
import repos.PetSQLRepository;
import repos.Repository;
import services.PetService;

/**
 *  Project 1:<br>
 * <br>
 *  The ShelterApplication class serves as the main console of interaction for Users.
 *  Using a while loop menu setup, a User can enter integer and String values associated to various options and
 *    details to specify when searching for, checking, adding or removing Items from the app.Menu's Catalog.
 *  The ShelterApplication class takes the role of the Singleton class of the Singleton design pattern set up to not
 *    allow the existence of more than one ShelterApplication at a time when running the application.
 *
 *  <br> <br>
 *  Created: <br>
 *     24 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     24 April 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped Constructor, getter methods. Looking to apply Singleton.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 24 April 2020
 */

public class ShelterApplication extends Application implements Runnable {
	// Instance Variables
	private static ShelterApplication uniqueInstance;
	private PetService petServ;

	// Constructor
	private ShelterApplication() {
		// Security layer through run environment variables
		String url = System.getenv("ENV_VAR_P1_POSTGRESQL_DB_URL");
		String username = System.getenv("ENV_VAR_P1_ADMIN_USERNAME");
		String password = System.getenv("ENV_VAR_P1_ADMIN_PASSWORD");
		String defaultSchema = System.getenv("ENV_VAR_P1_POSTGRESQL_DB_DEFAULT_SCHEMA");
		// Theoretically, this is where user authentication would go.

		// Initialize Dependencies
		ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
		Repository<Pet,Integer> petRepo = new PetSQLRepository(connectionUtil);

		// Initialize Services
		petServ = new PetService(petRepo);
	}

	// Getter Methods

	public static ShelterApplication getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new ShelterApplication();
		}
		return uniqueInstance;
	}

	// Methods

	public void run() {
		System.out.println("Manual Tests are good to go.");

		//Search for a Pet (successful)
		Pet searchedPet1 = petServ.searchByID(5674); // Should return Garfield
		searchedPet1.printInfo();
	}
}
