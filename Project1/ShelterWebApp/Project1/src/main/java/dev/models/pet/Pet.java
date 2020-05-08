package dev.models.pet;

/**
 *  Project 1:<br>
 * <br>
 *  The Pet class serves as a representation of a real-world pet that is currently held at the shelter, with information
 *      recorded as an entry in the shelter database.
 *  	Pet instances hold information of its real-world counterpart as variables.
 *  This class serves as the basis for all Pets as a parent class.
 *
 *  <br> <br>
 *  Created: <br>
 *     24 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     24 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented ID, name, breed, gender, and age variables.
 *     										    All are considered common across all kinds of Pets.
 *     										  Implemented printInfo signature, getters, setters, and printBaseInfo
 *     										    methods.
 * <br>
 *     28 April 2020, Barthelemy Martinon,    Added abstract method signature for getPetType.
 * <br>
 *     06 May   2020, Barthelemy Martinon,    Converted class to be non-abstract.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: 
 *  @version 24 April 2020
 */
public class Pet {
	//Instance Variables
	protected int ID;
	protected String name;
	protected String breed;
	protected String gender;
	protected int age;
	
	// Constructor
	
	public Pet(int ID, String name, String breed, String gender, int age) {
		this.ID = ID;
		this.name = name;
		this.breed = breed;
		this.gender = gender;
		this.age = age;
	}
	
	// Getter Methods
	
	public int getID() {
		return this.ID;
	}

	public String getName() { return name; }

	public String getBreed() { return breed; }

	public String getGender() { return gender; }

	public int getAge() { return age; }

	// Setter Methods

	public void setID(int ID) { this.ID = ID; }

	public void setName(String name) { this.name = name; }

	public void setBreed(String breed) { this.breed = breed; }

	public void setGender(String gender) { this.gender = gender; }

	public void setAge(int age) { this.age = age; }

	// Methods

	public String getPetType() {
		return null;
	}

	public String printInfo() {
		return null;
	}

	/**
	 * Prints the information that is available on all Pets
	 */
	public String printBaseInfo() {
		String output = (" ID # " + this.getID() + " | Name: " + this.getName() + " | Breed: " + this.getBreed() +
				" | Gender: " + this.getGender() + " | Age: " + this.getAge());
		return output;
	}
}
