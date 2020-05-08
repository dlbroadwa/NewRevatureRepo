package dev.models.pet;

/**
 *  Project 0:<br>
 * <br>
 *  The Dog class serves as a representation of a real-world dog found in the Shelter system.
 *  	Dog instances hold information of its real-world counterpart as variables that are unique to dogs.
 *  This class serves one of Pet's child classes.
 *
 *  <br> <br>
 *  Created: <br>
 *     24 April 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     24 April 2020, Barthelemy Martinon,    Created class.
 *     										  Implemented printInfo.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 24 April 2020
 */
public class Dog extends Pet {
	// Instance Variables
	private final String petType;
	
	// Constructors
	
	public Dog(int ID, String name, String breed, String gender, int age) {
		super(ID, name, breed, gender, age);
		this.petType = "dog";
	}

	// Getter Methods
	
	public String getPetType() {
		return this.petType;
	}
	
	// Methods

	/**
	 * Prints an instance's attribute information.
	 * Runs printBaseInfo first and prints any other information unique to a Novel instance.
	 */
	@Override
	public String printInfo() {
		String output = this.getPetType() + ": " + this.printBaseInfo();
		return output;
	}
}
