package book;

/**
 *  Project 0:<br>
 * <br>
 *  The Novel class serves as a representation of a real-world novel found in the Library system.
 *  	Novel instances hold information of its real-world counterpart as variables that are unique to
 *      novels.
 *  This class serves one of Item's child classes.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented genre variable;
 *     										    These variables are considered to be unique to Novels.
 *     											Inherits Item's variables.
 *     										  Implemented getter methods unique to Novel, and printInfo methods.
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 07 April 2020
 */
public class Novel extends Item {
	// Instance Variables
	String genre;
	
	// Constructors
	
	public Novel(int ID, String title, String author, String publisher, int year, String genre){
		super(ID, title, author, publisher, year);
		this.genre = genre;
	}

//	public Novel(Book base, String genre){
//		super(base.ID, base.title, base.author, base.publisher, base.year);
//		this.genre = genre;
//	}
	
	// Getter Methods
	
	public String getGenre() {
		return this.genre;
	}
	
	// Methods

	/*
	 * Prints an instance's attribute information.
	 * Runs printBaseInfo first and prints any other information unique to a Novel instance.
	 */
	@Override
	public void printInfo() {
		this.printBaseInfo();
		System.out.println("Genre: " + this.getGenre());
	}
}
