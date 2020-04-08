package book;

/**
 *  Project 0:<br>
 * <br>
 *  The Book class serves as a representation of a real-world book found in the Library system.
 *  	Book instances hold information of its real-world counterpart as variables.
 *  This class serves as the basis for all Books as a parent class.
 *
 *  <br> <br>
 *  Created: <br>
 *     07 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     07 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented ID, checkedStatus, title, author, publisher, and year variables.
 *     										    All of these variables are considered common
 *     										    across all kinds of Books.
 *     										  Implemented getter methods, toggleCheckStatus and printBaseInfo methods.
 *                                            
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 07 April 2020
 */
public abstract class Book {
	//Instance Variables
	protected int ID;
	protected boolean checkStatus;    // Boolean determines if Book is considered checked in (true) or out (false)
	protected String title;
	protected String author;
	protected String publisher;
	protected int year;
	
	// Constructor
	
	public Book(int ID, String title, String author, String publisher, int year) {
		this.ID = ID;
		this.checkStatus = true;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.year = year;
	}
	
	// Getter Methods
	
	public int getID() {
		return this.ID;
	}
	
	public boolean getCheckStatus() {
		return this.checkStatus;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getPublisher() {
		return this.publisher;
	}
	
	public int getYear() {
		return this.year;
	}
	
	// Methods
	
	public void toggleCheckStatus() {
		if ( this.getCheckStatus() == true ) {
			this.checkStatus = false;
		} else {
			this.checkStatus = true;
		}
	}
	
	public abstract void printInfo();
	
	public void printBaseInfo() {
		System.out.println("ID # " + this.getID());
		System.out.println("Check Status: " + this.getCheckStatus());
		System.out.println("Title: " + this.getTitle());
		System.out.println("Author: " + this.getAuthor());
		System.out.println("Publisher: " + this.getPublisher());
		System.out.println("Year: " + this.getYear());
	}
}
