package data;

import book.Item;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Project 0:<br>
 * <br>
 *  The Catalog class serves as a representation of a library's collection/storage of books, where a librarian can
 *    can search through, retrieve content, add new or remove content from the collection, etc.
 *  This will serve as a collection of Book subclass instances that Admins can add/remove to/from, retrieve/return
 *    for/from Users, upkeep Book information and check to see if an Item is checked out or not.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented bookList ArrayList for book storage.
 *     										  Implemented getter, isCatalogEmpty, searchByID, addNewBook, 
 *     											and removeBook methods.                                
 * <br>
 *     08 April 2020, Barthelemy Martinon,    Implemented checkIn, and checkOut methods.
 *     										  Added Scanner element for user input.
 * <br>
 *     09 April 2020, Barthelemy Martinon,    Implemented updateCatalog method.
 *     										    Will serve as the main method to update catalog content with
 *     											local fileIO and database table storage.
 * <br>
 * 
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 09 April 2020
 */
public class Catalog {
	// Instance Variable
	private ArrayList<Item> itemList; // Array to store all books found in the library.
	private Scanner scanner;
	private FileIODAO fileIO;
	
	// Constructor
	public Catalog() {
		//this.bookList = new ArrayList<Book>();
		this.scanner = new Scanner(System.in);
		fileIO = new FileIODAO("src/main/java/resources/catalogcontent");
		this.itemList = fileIO.getCatalogContent();
	}
	
	// TODO Possibly implement a File I/O variant of a constructor to retrieve persistent data from input file
	
	
	// TODO Possibly implement a SQL variant of a constructor to retrieve persistent data from database
	
	// Getter Methods
	
	public ArrayList<Item> getItemList() {
		return itemList;
	}
	
	public Scanner getScanner() {
		return scanner;
	}
	
	// Methods
	
	public boolean isCatalogEmpty() {
		if ( itemList.size() == 0 ) {
			System.out.println("NOTICE: Catalog is EMPTY!");
			return true;
		} else {
			return false;
		}
	}
	
	public Item searchByID(int idInput) {
		Item result = null;
		for ( Item b : itemList) {
			if ( b.getID() == idInput ) {
				result = b;
			}
		}
		return result;
	}
	
	public void addNewBook(Item newItem) {
		int newBookID = newItem.getID();
		Item searchResult = searchByID(newBookID);
		if ( searchResult == null ) {
			itemList.add(newItem);
		} else {
			System.err.println("ERROR: New Entry contains non-unique ID. All new entries must have a unique ID.");
		}
	}
	
	public void removeBook(int idInput) {
		Item searchResult = searchByID(idInput);
		if ( searchResult != null ) {
			itemList.remove(searchResult);
		} else {
			System.err.println("ERROR: No item with the given ID is found. Please try again.");
		}
	}
	
	public void checkOut(int idInput) {
		boolean confirmation = false;
		for ( Item b : itemList) {
			if ( b.getID() == idInput ) {
				if ( !b.getCheckStatus() ) {
					System.err.println("ERROR: Item ID# " + b.getID() + " is not available as it is already checked out.");
					confirmation = true;
				} else {
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
	
	public void checkIn(int idInput) {
		boolean confirmation = false;
		for ( Item b : itemList) {
			if ( b.getID() == idInput ) {
				if ( b.getCheckStatus() ) {
					System.err.println("ERROR: Item ID# " + b.getID() + " is already checked in.");
					confirmation = true;
				} else {
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
	
	public void updateCatalog() {
		fileIO.recordData(this);
	}
}
