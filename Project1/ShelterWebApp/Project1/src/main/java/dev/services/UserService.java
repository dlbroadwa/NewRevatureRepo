package dev.services;

import dev.models.user.User;
import dev.repos.Repository;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Project 1:<br>
 * <br>
 *  The UserService class serves as a representation of an access point for the system's User data stored on the
 *    appropriate postgresql database table where a shelter worker can search through, retrieve content, add new, update
 *    or remove content from the collection.
 *  This will serve as a means of interaction with a collection of User subclass instances that Admins can perform the
 *    requested actions mentioned above when tasked to do so.
 *
 *  <br> <br>
 *  Created: <br>
 *     01 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     01 May 2020, Barthelemy Martinon,    Created class.
 *     										Prototyped cosntructor, instance variables, getters, setters, searchByID,
 *     										  addNewUser, removeUser and updateUser around initial (current) projected
 *     										  idea of structure based around current User design.
 *     										Very much subject to change in near future.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 01 May 2020
 */
public class UserService {
    // Instance Variables
    private ArrayList<User> userList;
    private Scanner scanner;
    private Repository<User, Integer> userSQLRepo = null;

    // Constructor
    public UserService(Repository<User, Integer> userSQLRepo) {
        this.scanner = new Scanner(System.in);
        this.userSQLRepo = userSQLRepo;
        this.userList = userSQLRepo.findAll();
    }

    // Getter Methods

    public ArrayList<User> getPetList() { return userList; }

    public Scanner getScanner() { return scanner; }

    public Repository<User, Integer> getUserSQLRepo() { return userSQLRepo; }

    // Setter Methods

    public void setPetList(ArrayList<User> petList) { this.userList = petList; }

    public void setScanner(Scanner scanner) { this.scanner = scanner; }

    public void setUserSQLRepo(Repository<User, Integer> userSQLRepo) { this.userSQLRepo = userSQLRepo; }

    // Methods

    /**
     * Goes through the Repository's content to search for a user with the provided ID value.
     * Returns the target User if found, and null otherwise.
     *
     * 	@param idInput Specified ID Number being searched for
     *
     * 	@return result Target user (or null if none is found)
     */
    public User searchByID(int idInput) {
        return userSQLRepo.findById(idInput);
    }

    /**
     * Adds a new User (given as input) into the user list.
     * Does nothing if a user with an ID that already exists is being passed.
     *
     * 	@param newItem New user to add to the Repository user list.
     *
     *  @return result User that was just added
     */
    public User addNewUser(User newUser) {
        int newPetID = newUser.getID();
        User searchResult = searchByID(newPetID);
        if ( searchResult == null ) {
            userSQLRepo.save(newUser);
            return newUser;
        } else {
            System.err.println("ERROR: New Entry contains non-unique ID. All new entries must have a unique ID.");
        }
        return searchResult;
    }

    /**
     * Removes a User that matches the ID input.
     * Returns the removed user's information when completed.
     * Returns null if no user with that ID exists.
     *
     * 	@param idInput Specified ID Number of User that must be removed
     *
     * 	@return result User that was just removed
     */
    public User removeUser(int idInput) {
        User searchResult = searchByID(idInput);
        if ( searchResult != null ) {
            userSQLRepo.delete(searchResult);
        } else {
            System.err.println("ERROR: No user with the given ID is found. Please try again.");
        }
        return searchResult;
    }

    /**
     * Updates a Pet that matches the ID input with the values given as a Pet input.
     * Does nothing if no pet with that ID exists.
     *
     * 	@param idInput Specified ID Number of Pet that must be removed
     */
    public User updateUser(User newUser, int idInput) {
        User searchResult = searchByID(idInput);
        if ( searchResult != null ) {
            userSQLRepo.update(newUser, idInput);
        } else {
            System.err.println("ERROR: No user with the given ID is found. Please try again.");
        }
        return searchResult;
    }
}
