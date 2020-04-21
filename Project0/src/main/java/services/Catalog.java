package services;

import data.DAO;
import data.FileIODAO;
import data.SqlDAO;
import models.Item;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The Catalog class serves as a representation of a library's collection/storage of books, where a librarian can
 *    can search through, retrieve content, add new or remove content from the collection, etc.
 *  This will serve as a means of interaction with a collection of Book subclass instances that Admins can add/remove
 *    to/from, retrieve/return for/from Users, upkeep Book information and check to see if an Item is checked out or not.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented bookList ArrayList for book storage.
 *     										  Implemented getter, isCatalogEmpty, searchByID, addNewBook, 
 *     											and removeBook methods.                                
 * <br>
 *     08 April 2020, Barthelemy Martinon,    Implemented checkIn, and checkOut methods.
 *     										  Added Scanner element for unused.user input.
 * <br>
 *     09 April 2020, Barthelemy Martinon,    Implemented updateCatalog method.
 *     										    Will serve as the main method to update catalog content with
 *     											local fileIO and database table storage.
 * <br>
 *     15 April 2020, Barthelemy Martinon,    Phased out hard implementation of FileIODAO with DAO interface, which
 *     											will now default to SqlDAO instance.
 * <br>
 *     16 April 2020, Barthelemy Martinon,    Overloaded constructor to include a variant which takes a username and
 *     										  	password for the new SqlDAO setup.
 *     										  Old constructor with no parameters and FileIO setup will be delegated
 *     										    for non-database jUnit tests. (See first 10 tests of TestLibrary.java)
 *     										  Introduced dao.updateCheck calls into checkIn and checkOut methods.
 * <br>
 *     17 April 2020, Barthelemy Martinon,    Introduced addItem and removeItem calls into addNewBook and
 *     											removeBook methods.
 * <br>
 *     20 April 2020, Barthelemy Martinon,    Moved Catalog into separate services directory to clear confusion
 *     											regarding its role as a "model" that is performing processes.
 *     										  Reworked constructor to take a DAO instance as a parameter to allow
 *     										    for Catalog to take upon specific configurations for db connections
 *     										    through DAO (for either local or database). Allows for an easier way
 *     										    to access, manipulate or replace the DAO instance through getters and
 *     										    setters.
 * <br>
 *     21 April 2020, Barthelemy Martinon,    Reworked searchByID method to contact the dao for the list of Items to
 *     											go through.
 *     										  Implemented displayAll method to display a condensed list of Items for
 *     										    quick access to all entries and their ID numbers.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 21 April 2020
 */
public class Catalog {
	// Instance Variables
	private ArrayList<Item> itemList; // Array to store all books found in the library. Mainly used for FileIO.
	private Scanner scanner;
	private DAO dao;
	
	// Constructors
	// Uses old FileIO setup for jUnit tests.
	public Catalog() {
		this.scanner = new Scanner(System.in);
		this.dao = new FileIODAO("src/main/java/resources/catalogcontent");
		this.itemList = dao.getContent();
	}

	// Uses Sql database setup for main application
	// Takes a DAO as a parameter
	public Catalog(DAO dao) {
		this.scanner = new Scanner(System.in);
		this.dao = dao;
		this.itemList = dao.getContent();
	}
	
	// Getter Methods
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	public Scanner getScanner() {
		return scanner;
	}

	public DAO getDao() { return dao; }

	// Setter Methods

	public void setItemList(ArrayList<Item> itemList) {
		this.itemList = itemList;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public void setDao(DAO dao) {
		this.dao = dao;
	}

	// Methods

	/*
	 * Check the itemList size for if the ArrayList is empty (size 0)
	 * Returns the appropriate boolean for the conditional.
	 */
	public boolean isCatalogEmpty() {
		if ( itemList.size() == 0 ) {
			System.err.println("NOTICE: Catalog is EMPTY!");
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Iterates through the itemlist via For Loop for an Item with the specified ID
	 * Returns an Item if found, and null otherwise.
	 *
	 * 	@param idInput Specified ID Number being searched for
	 *
	 * 	@return result Target item (or null if none is found)
	 */
	public Item searchByID(int idInput) {
		return dao.getItem(idInput);
	}

	/*
	 * Takes the item list and prints into the console all entries condensed into single lines displaying their idNum,
	 *   title and author.
	 *
	 * 	@param idInput Specified ID Number being searched for
	 *
	 * 	@return result Target item (or null if none is found)
	 */
	public void displayAll() {
		ArrayList<Item> allItems = dao.getContent();
		System.out.println("Displaying all entries!");
		System.out.println(" ");
		for ( Item b : allItems ) {
			System.out.println("ID# " + b.getID() + " | Title: " + b.getTitle() + " | Author: " + b.getAuthor());
		}
		System.out.println(" ");
	}

	/*
	 * Adds a new Item (given as input) into the Catalog.
	 * Does nothing if an item with an ID that already exists is being passed.
	 *
	 * 	@param newItem New item to add to Catalog
	 */
	public void addNewBook(Item newItem) {
		int newBookID = newItem.getID();
		Item searchResult = searchByID(newBookID);
		if ( searchResult == null ) {
			dao.addItem(newItem);
			itemList.add(newItem);
		} else {
			System.err.println("ERROR: New Entry contains non-unique ID. All new entries must have a unique ID.");
		}
	}

	/*
	 * Removes an Item that matches the ID input.
	 * Does nothing if no item with that ID exists..
	 *
	 * 	@param idInput Specified ID Number of Item that must be removed
	 */
	public void removeBook(int idInput) {
		Item searchResult = searchByID(idInput);
		if ( searchResult != null ) {
			dao.removeItem(searchResult);
			itemList.remove(searchResult);
		} else {
			System.err.println("ERROR: No item with the given ID is found. Please try again.");
		}
	}

	/*
	 * Checks out an Item with the ID given as input.
	 * Does nothing if no item the input ID exists or if its checkStatus attribute is false.
	 *   (i.e. already checked out)
	 *
	 * 	@param idInput ID number of Item that must be checked out.
	 */
	public void checkOut(int idInput) {
		boolean confirmation = false;
		for ( Item b : itemList) {
			if ( b.getID() == idInput ) {
				if ( !b.getCheckStatus() ) {
					System.err.println("ERROR: Item ID# " + b.getID() + " is not available as it is already checked out.");
					confirmation = true;
				} else {
					dao.updateCheck(b,0);
					b.toggleCheckStatus();
					System.out.println("Successfully checked out item ID# " + b.getID() + " !");
					confirmation = true;
				}
			}
		}
		if ( !confirmation ) {
			System.out.println("ERROR: Item ID# " + idInput + " does not exist in the system.");
		}
	}

	/*
	 * Checks in an Item with the ID given as input.
	 * Does nothing if no item the input ID exists or if its checkStatus attribute is true.
	 *   (i.e. already checked in)
	 *
	 * 	@param idInput ID number of Item that must be checked in.
	 */
	public void checkIn(int idInput) {
		boolean confirmation = false;
		for ( Item b : itemList) {
			if ( b.getID() == idInput ) {
				if ( b.getCheckStatus() ) {
					System.err.println("ERROR: Item ID# " + b.getID() + " is already checked in.");
					confirmation = true;
				} else {
					dao.updateCheck(b,1);
					b.toggleCheckStatus();
					System.out.println("Successfully checked in item ID# " + b.getID() + " !");
					confirmation = true;
				}
			}
		}
		if ( !confirmation ) {
			System.err.println("ERROR: Item ID# " + idInput + " does not exist in the system.");
		}
	}

	/*
	 * Runs Catalog's FileIODAO's recordData method to update the local storage file's contents with the Catalog's
	 *   current state.
	 */
	public void recordCatalogToFile() {
		dao.recordData(this);
	}
}
