package data;

import java.util.ArrayList;

import book.Book;

/**
 *  Project 0:<br>
 * <br>
 *  The Catalog class serves as a representation of a library's collection/storage of books, where a librarian can
 *    can search through, retrieve content, add new or remove content from the collection, etc.
 *  This will serve as a collection of Book subclass instances that Admins can add/remove to/from, retrieve/return for/from
 *    Members, upkeep Book information and check to see if a Book is checked out or not.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented bookList ArrayList for book storage.
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 07 April 2020
 */
public class Catalog {
	// Instance Variable
	ArrayList<Book> bookList; // Array to store all books found in the library.
	
	// Constructor
	public Catalog() {
		this.bookList = new ArrayList<Book>();
	}
	
	// TODO Possibly implement a File I/O variant of a constructor to retrieve persistent data from input file
	
	// TODO Possibly implement a SQL variant of a constructor to retrieve persistent data from database
	
	// Methods
	
	public boolean isCatalogEmpty() {
		if ( bookList.size() == 0 ) {
			System.out.println("NOTICE: Catalog is EMPTY!");
			return true;
		} else {
			return false;
		}
	}
	
	public Book searchByID(int idInput) {
		Book result = null;
		for ( Book b : bookList ) {
			if ( b.getID() == idInput ) {
				result = b;
			}
		}
		return result;
	}
	
	public void addNewBook(Book newBook) {
		int newBookID = newBook.getID();
		Book searchResult = searchByID(newBookID);
		if ( searchResult == null ) {
			bookList.add(newBook);
		} else {
			System.out.println("ERROR: New Entry contains non-unique ID. All new entries must have a unique ID.");
		}
	}
	
	public void removeBook(int idInput) {
		Book searchResult = searchByID(idInput);
		if ( searchResult != null ) {
			bookList.remove(searchResult);
		} else {
			System.out.println("ERROR: No item with the given ID is found. Please try again.");
		}
	}
	
	public void checkOut(int idInput) {
//		for ( Book b : bookList ) {
//			if ( b.getID() == idInput && b.getCheckStatus() == true ) {
//				b.toggleCheckStatus();
//				System.out.println("Successfully checked out item ID# " + b.getID() + " !");
//				break;
//			} else if ( b.getID() == idInput && b.getCheckStatus() == false ) {
//				System.out.println("ERROR: Item ID# " + b.getID() + " is not available as it is already checked out.");
//				break;
//			}
//		}
//		System.out.println("ERROR: Item ID# " + idInput + " does not exist in the system.");
		boolean confirmation = false;
		for ( Book b : bookList ) {
			if ( b.getID() == idInput ) {
				if ( !b.getCheckStatus() ) {
					System.out.println("ERROR: Item ID# " + b.getID() + " is not available as it is already checked out.");
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
		for ( Book b : bookList ) {
			if ( b.getID() == idInput && b.getCheckStatus() == false ) {
				b.toggleCheckStatus();
				System.out.println("Successfully checked in item ID# " + b.getID() + " !");
				break;
			} else if ( b.getID() == idInput && b.getCheckStatus() == true ) {
				System.out.println("ERROR: Item ID# " + b.getID() + " is not available as it is already checked out.");
				break;
			}
		}
		System.out.println("ERROR: Item ID# " + idInput + " does not exist in the system.");
	}
	
}
