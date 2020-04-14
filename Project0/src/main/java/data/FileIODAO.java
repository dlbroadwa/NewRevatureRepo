package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import book.Dictionary;
import book.Item;
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
 *     										  Implemented file input upon FileIODAO instance creation via
 *     										    FileReader and BufferedFileReader to catalogcontent file.
 * <br>
 *     09 April 2020, Barthelemy Martinon,    Implemented file output on recordData method call via
 *     										    PrintWriter, FileWriter and BufferedFileWriter.
 *     										  PrintWriter's purpose is to empty out the file before readding
 *     											book information.
 * <br>
 * 
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 09 April 2020
 */

public class FileIODAO {
	// Instance Variables
	ArrayList<Item> items = null;
	String catalogContentFilePath = null;
	
	// Constructor 
	public FileIODAO(String filePath) {
		catalogContentFilePath = filePath;
		FileReader reader = null;
		BufferedReader bReader = null;
		items = new ArrayList<Item>();
		
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
					Item newDictionary = new Dictionary(newID, newTitle, newAuthor, newPublisher, newYear, newLanguage,
							newWordCount);
					items.add(newDictionary);
				} else if ( bookInfo.get(0).compareTo("N") == 0 ) {
					String newGenre = bookInfo.get(6);
					Item newNovel = new Novel(newID, newTitle, newAuthor, newPublisher, newYear, newGenre);
					items.add(newNovel);
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error loading content file, using backup");
			//answers = new String[]{"Try again later."};
			Item d = new Dictionary(12345, "TheBigBookofWords", "ProfessorWright",
					"WordsmithInc.", 2015, "English", 9876);
			Item n = new Novel(67890, "TheGreatAdventure", "ArthurEnglish", "StorytimeLLC",
					2009, "Drama");
			items.add(d);
			items.add(n);
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	// Getter Methods
	
	public ArrayList<Item> getCatalogContent() {
		return items;
	}
	
	// Methods

	/*
	 * Takes the local storage file, empties it using PrintWriter and refills it using BufferedWriter with the
	 *   current data state of the Catalog given as input.
	 *
	 * 	@param idInput ID number of Item that must be checked out.
	 */
	public void recordData(Catalog catalog) {
		FileWriter writer = null;
		BufferedWriter bWriter = null;
		
		try {
			// Clear file contents before writing new lines
			PrintWriter pWriter = new PrintWriter(catalogContentFilePath);
			pWriter.print("");
			pWriter.close();
			
			writer = new FileWriter(catalogContentFilePath);
			bWriter = new BufferedWriter(writer);
			
			ArrayList<Item> itemList = catalog.getItemList();
			
			for ( Item b : itemList) {
				String bookInfo = "";
				if ( b instanceof Dictionary ) {
					bookInfo = "D " + b.getID() + " " + b.getTitle() + " " + b.getAuthor() + " " + b.getPublisher() +
							" " + b.getYear();
					Dictionary d = (Dictionary) b;
					bookInfo = bookInfo + " " + d.getLangauge() + " " + d.getWordCount();
				} else if ( b instanceof Novel ) {
					bookInfo = "N " + b.getID() + " " + b.getTitle() + " " + b.getAuthor() + " " + b.getPublisher() +
							" " + b.getYear();
					Novel n = (Novel) b;
					bookInfo = bookInfo + " " + n.getGenre();
				}
				bWriter.write(bookInfo);
				bWriter.newLine();
			}
			bWriter.flush();
			bWriter.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error loading content file, abort process, no changes (should) be made.");
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
