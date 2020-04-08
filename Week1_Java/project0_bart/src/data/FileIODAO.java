package data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import book.Book;
import book.Dictionary;
import book.Novel;

/**
 *  Project 0:<br>
 * <br>
 *  The FileIODAO class serves as a means to obtain Book information from a persistent data file or database
 *    to allow for the catalog to persist between sessions in terms of changes.
 *  The FileIODAO as of 4/8/2020 will be designed to include local File I/O.
 *
 *  <br> <br>
 *  Created: <br>
 *     08 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     08 April 2020, Barthelemy Martinon,    Created class.
 *     										  
 * 
 * <br>
 * 
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 08 April 2020
 */

public class FileIODAO {
	// Instance Variables
	ArrayList<Book> books = null;
	
	// Constructor 
	public FileIODAO(String catalogContentFilePath) {
		FileReader reader = null;
		BufferedReader bReader = null;
		books = new ArrayList<Book>();
		
		try {
			reader = new FileReader(catalogContentFilePath);
			bReader = new BufferedReader(reader);
			
			String line = "";
			while ( (line = bReader.readLine()) != null ) {
				ArrayList<String> bookInfo = new ArrayList<String>();
				String[] splitline = line.split("\\s+");
				
				for (String s : splitline) {
					bookInfo.add(s);
				}
				
				int newID = Integer.parseInt(bookInfo.get(1));
				
				String newTitle = bookInfo.get(2);
				String newAuthor = bookInfo.get(3);
				String newPublisher = bookInfo.get(4);
				int newYear = Integer.parseInt(bookInfo.get(5));

				if ( bookInfo.get(0).compareTo("D") == 0 ) {
					String newLanguage = bookInfo.get(6);
					int newWordCount = Integer.parseInt(bookInfo.get(7));
					Book newDictionary = new Dictionary(newID, newTitle, newAuthor, newPublisher, newYear, newLanguage, newWordCount);
					books.add(newDictionary);
				} else if ( bookInfo.get(0).compareTo("N") == 0 ) {
					String newGenre = bookInfo.get(6);
					Book newNovel = new Novel(newID, newTitle, newAuthor, newPublisher, newYear, newGenre);
					books.add(newNovel);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error loading content file, using backup");
			//answers = new String[]{"Try again later."};
			Book d = new Dictionary(12345, "The Big Book of Words", "Professor Wright", "Wordsmith Inc.", 2015, "English", 9876);
			Book n = new Novel(67890, "The Great Adventure", "Arthur English", "Storytime LLC", 2009, "Drama");
			books.add(d);
			books.add(n);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// Method
	
	public ArrayList<Book> getCatalogContent() {
		return books;
	}
}
