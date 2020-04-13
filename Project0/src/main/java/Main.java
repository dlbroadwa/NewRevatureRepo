import book.Item;
import data.Catalog;
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
 * <br>
 *     08 April 2020, Barthelemy Martinon,    Began using class to perform rudimentary unit 
 *     											tests on Books and Catalog, and their methods
 * <br>
 *     09 April 2020, Barthelemy Martinon,    Began using class to perform rudimentary unit 
 *     											tests on Catalog's FileIO system
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 09 April 2020
 */

public class Main {

	public Main() {
		Menu m = new Menu();
		m.run();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main m = new Main();

//		// Testing Book subclasses
//		Book d = new Dictionary(12345, "The Big Book of Words", "Professor Wright", "Wordsmith Inc.", 2015, "English", 9876);
//		d.printInfo();
//		
//		Book n = new Novel(67890, "The Great Adventure", "Arthur English", "Storytime LLC", 2009, "Drama");
//		n.printInfo();
//		
//		// Testing Catalog
//		Catalog c = new Catalog();
//		
//		// searchByID addNewBook
//		System.out.println(c.searchByID(12345));
//		c.addNewBook(d);
//		Book x = c.searchByID(12345);
//		x.printInfo();
//		
//		System.out.println(c.searchByID(67890));
//		c.addNewBook(n);
//		Book y = c.searchByID(67890);
//		y.printInfo();
//		
//		// Adding a new book with a non-unique ID
//		c.addNewBook(n);
//		
//		// removeBook
//		c.removeBook(12345);
//		System.out.println(c.searchByID(12345));
//		c.removeBook(12345);
//		
//		// checkOut
//		c.checkOut(67890);
//		System.out.println((c.searchByID(67890)).getCheckStatus());
//		c.checkOut(67890);
//		c.checkOut(12345);
//		
//		// checkIn
//		c.checkIn(67890);
//		System.out.println((c.searchByID(67890)).getCheckStatus());
//		c.checkIn(67890);
//		c.checkOut(12345);
//
//		Catalog c = new Catalog();
//		int i = 1;
//		for ( Item b : c.getItemList() ) {
//			System.out.println("Book #" + i);
//			b.printInfo();
//			i++;
//		}
//
//		c.updateCatalog();
//
//		Item n1 = new Novel(12357, "TomorrowWorld", "AlfonseUno", "BeyondPages", 2016, "Sci-Fi");
//		Item d1 = new Dictionary(1123, "VivaEspana", "SonyaDos", "WorldSpeaker", 2004, "Spanish", 6543);
//
//		c.addNewBook(n1);
//		c.addNewBook(d1);
//
//		c.updateCatalog();
//
//		int j = 1;
//		for ( Item b : c.getItemList() ) {
//			System.out.println("Book #" + j);
//			b.printInfo();
//			j++;
//		}
	}
}
