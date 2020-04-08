import data.Catalog;
import book.Book;
import book.Dictionary;
import book.Novel;


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
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 07 April 2020
 */

public class LibraryMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LibraryMain m = new LibraryMain();
		
		// Testing Book subclasses
		Book d = new Dictionary(12345, "The Big Book of Words", "Professor Wright", "Wordsmith Inc.", 2015, "English", 9876);
		d.printInfo();
		
		Book n = new Novel(67890, "The Great Adventure", "Arthur English", "Storytime LLC", 2009, "Drama");
		n.printInfo();
		
		// Testing Catalog
		Catalog c = new Catalog();
		
		// searchByID addNewBook
		System.out.println(c.searchByID(12345));
		c.addNewBook(d);
		Book x = c.searchByID(12345);
		x.printInfo();
		
		System.out.println(c.searchByID(67890));
		c.addNewBook(n);
		Book y = c.searchByID(67890);
		y.printInfo();
		
		// Adding a new book with a non-unique ID
		c.addNewBook(n);
		
		// removeBook
		c.removeBook(12345);
		System.out.println(c.searchByID(12345));
		c.removeBook(12345);
		
		// checkOut
		c.checkOut(67890);
		c.checkOut(67890);
	}

}