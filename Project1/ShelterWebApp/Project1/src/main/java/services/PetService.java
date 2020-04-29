package services;

import models.Pet;
import repos.Repository;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Project 1:<br>
 * <br>
 *  The PetService class serves as a representation of an access point for the system's Pet data stored on the
 *    appropriate postgresql database table where a shelter worker can search through, retrieve content, add new, update
 *    or remove content from the collection.
 *  This will serve as a means of interaction with a collection of Pet subclass instances that Employees can perform the
 *    requested actions mentioned above when tasked to do so.
 *
 *  <br> <br>
 *  Created: <br>
 *     29 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     29 April 2020, Barthelemy Martinon,    Created class.
 *     										  Prototyped cosntructor, instance variables, getters, setters, searchByID,
 *     										    addNewPet, removePet and updatePet around initial (current) projected
 *     										    idea of structure based around current Pet design.
 *     										  Very much subject to change in near future.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 29 April 2020
 */
public class PetService {
    // Instance Variables
    private ArrayList<Pet> petList; // Array to store all books found in the library. Mainly used for FileIO.
    private Scanner scanner;
    private Repository<Pet, Integer> petSQLRepo = null;

    // Constructor
    public PetService(Repository<Pet, Integer> petSQLRepo) {
        this.scanner = new Scanner(System.in);
        this.petSQLRepo = petSQLRepo;
        this.petList = petSQLRepo.findAll();
    }

    // Getter Methods

    public ArrayList<Pet> getPetList() { return petList; }

    public Scanner getScanner() { return scanner; }

    public Repository<Pet, Integer> getPetSQLRepo() { return petSQLRepo; }

    // Setter Methods

    public void setPetList(ArrayList<Pet> petList) { this.petList = petList; }

    public void setScanner(Scanner scanner) { this.scanner = scanner; }

    public void setPetSQLRepo(Repository<Pet, Integer> petSQLRepo) { this.petSQLRepo = petSQLRepo; }

    // Methods

    /*
     * Goes through the Repository's content to search for a pet with the provided ID value.
     * Returns the target Pet if found, and null otherwise.
     *
     * 	@param idInput Specified ID Number being searched for
     *
     * 	@return result Target pet (or null if none is found)
     */
    public Pet searchByID(int idInput) {
        return petSQLRepo.findById(idInput);
    }

    /*
     * Adds a new Pet (given as input) into the pet list.
     * Does nothing if an item with an ID that already exists is being passed.
     *
     * 	@param newItem New pet to add to the Repository pet list.
     */
    public void addNewPet(Pet newPet) {
        int newPetID = newPet.getID();
        Pet searchResult = searchByID(newPetID);
        if ( searchResult == null ) {
            petSQLRepo.save(newPet);
        } else {
            System.err.println("ERROR: New Entry contains non-unique ID. All new entries must have a unique ID.");
        }
    }

    /*
     * Removes a Pet that matches the ID input.
     * Does nothing if no pet with that ID exists.
     *
     * 	@param idInput Specified ID Number of Pet that must be removed
     */
    public void removePet(int idInput) {
        Pet searchResult = searchByID(idInput);
        if ( searchResult != null ) {
            petSQLRepo.delete(searchResult);
        } else {
            System.err.println("ERROR: No pet with the given ID is found. Please try again.");
        }
    }

    /*
     * Updates a Pet that matches the ID input with the values given as a Pet input.
     * Does nothing if no pet with that ID exists.
     *
     * 	@param idInput Specified ID Number of Pet that must be removed
     */
    public void updatePet(Pet newPet, int idInput) {
        Pet searchResult = searchByID(idInput);
        if ( searchResult != null ) {
            petSQLRepo.update(newPet, idInput);
        } else {
            System.err.println("ERROR: No pet with the given ID is found. Please try again.");
        }
    }
}
