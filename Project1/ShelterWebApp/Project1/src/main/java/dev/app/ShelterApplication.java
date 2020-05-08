package dev.app;

import dev.connections.ConnectionUtil;
import dev.connections.PostgresConnectionUtil;
import dev.models.pet.Dog;
import dev.models.pet.Pet;
import dev.models.user.Employee;
import dev.models.user.User;
import dev.repos.PetSQLRepository;
import dev.repos.Repository;
import dev.repos.UserSQLRepository;
import dev.services.PetService;
import dev.services.UserService;

/**
 *  Project 1:<br>
 * <br>
 *  The ShelterApplication class serves as the main console of interaction for Users.
 *  Using a while loop menu setup, a User can enter integer and String values associated to various options and
 *    details to specify when searching for, checking, adding or removing Items from the dev.app.Menu's Catalog.
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
 *     02 May 2020, Barthelemy Martinon,      Added getters for UserService and PetService.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 02 May 2020
 */

public class ShelterApplication extends Application implements Runnable {
	// Instance Variables
	private static ShelterApplication uniqueInstance;
	private PetService petServ;
	private UserService userServ;

	// Constructor
	private ShelterApplication() {
		// Security layer through run environment variables
//		String url = System.getenv("ENV_VAR_P1_POSTGRESQL_DB_URL");
//		String username = System.getenv("ENV_VAR_P1_ADMIN_USERNAME");
//		String password = System.getenv("ENV_VAR_P1_ADMIN_PASSWORD");
//		String defaultSchema = System.getenv("ENV_VAR_P1_POSTGRESQL_DB_DEFAULT_SCHEMA");

		String url = "jdbc:postgresql://shelterdatabase.c9bsvowbng02.us-east-1.rds.amazonaws.com:5432/postgres";
		String username = "shelter_admin";
		String password = "you_will_never_guess_my_password!";
		String defaultSchema = "project1schema";

		// Initialize Dependencies
		ConnectionUtil connectionUtil = new PostgresConnectionUtil(url, username, password, defaultSchema);
		Repository<Pet,Integer> petRepo = new PetSQLRepository(connectionUtil);
		Repository<User,Integer> userRepo = new UserSQLRepository(connectionUtil);

		// Initialize Services
		petServ = new PetService(petRepo);
		userServ = new UserService(userRepo);
	}

	// Getter Methods

	public static ShelterApplication getInstance() {
		if ( uniqueInstance == null ) {
			uniqueInstance = new ShelterApplication();
		}
		return uniqueInstance;
	}

	public PetService getPetServ() {
		return petServ;
	}

	public UserService getUserServ() {
		return userServ;
	}

	// Methods

	public void run() {
		System.out.println("Manual Tests are good to go.");

		//Search for a Pet (successful)
		Pet searchedPet1 = petServ.searchByID(5674); // Should return Garfield
		System.out.println(searchedPet1.printInfo());

		//Search for a Pet (failure)
		Pet searchedPet2 = petServ.searchByID(9999999); // Should return Garfield
		if ( searchedPet2 == null ) {
			System.out.println("Null returned. Test passed.");
		}

		// Add a new Pet and then search for it
		Pet newPet1 = new Dog(123, "Buddy", "GermShep", "m", 6);
		petServ.addNewPet(newPet1);
		Pet searchedPet3 = petServ.searchByID(123); // Should return Buddy
		System.out.println(searchedPet3.printInfo());

		// Update it with new information.
		Pet newPet1v2 = new Dog(123, "Atilla", "Husky", "m", 8);
		petServ.updatePet(newPet1v2,123);
		Pet searchedPet3v2 = petServ.searchByID(123); // Should return Atilla
		System.out.println(searchedPet3v2.printInfo());

		// And then remove it
		petServ.removePet(123);
		Pet searchedPet4 = petServ.searchByID(123); // Should fail
		if ( searchedPet4 == null ) {
			System.out.println("Null returned. Test passed.");
		}

		// User manual tests

		//Search for a User (successful)
		User searchedUser1 = userServ.searchByID(7777); // Should return Lucky Ducky
		System.out.println(searchedUser1.printBaseInfo());

		//Search for a User (failure)
		User searchedUser2 = userServ.searchByID(9999999); // Should return null
		if ( searchedUser2 == null ) {
			System.out.println("Null returned. Test passed.");
		}

		// Add a new User and then search for it
		User newUser1 = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");
		userServ.addNewUser(newUser1);
		User searchedUser3 = userServ.searchByID(67890); // Should return Bella
		System.out.println(searchedUser3.printBaseInfo());

		// Update it with new information.
		User newUser1v2 = new Employee("Buffy", "Winters", 67890, "bwinters", "oakstake");
		userServ.updateUser(newUser1v2,67890);
		User searchedUser3v2 = userServ.searchByID(67890); // Should return Buffy
		System.out.println(searchedUser3v2.printBaseInfo());

		// And then remove it
		userServ.removeUser(67890);
		User searchedUser4 = userServ.searchByID(67890); // Should fail
		if ( searchedUser4 == null ) {
			System.out.println("Null returned. Test passed.");
		}
	}
}
