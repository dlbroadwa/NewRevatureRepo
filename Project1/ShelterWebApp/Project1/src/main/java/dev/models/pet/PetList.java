package dev.models.pet;

import java.util.ArrayList;
import java.util.List;

/**
 *  Project 1:<br>
 * <br>
 *  The PetList class serves as a wrapper for a Pet ArrayList.
 *  PetList is used by PetServlet and PetService to obtain said list to prepare it for JSON conversion to be used
 *    for the search functionality of adoption page.
 *
 *  <br> <br>
 *  Created: <br>
 *     06 May 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet, Daniel Wallace<br>
 *  Modifications: <br>
 *     06 May 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet, Daniel Wallace
 *  @version 06 May 2020
 */
public class PetList {
    // Instance Variables
    public List<Pet> pets;

    // Constructors
    public PetList() { pets = new ArrayList<>(); }

    public PetList(ArrayList<Pet> petlist) { pets = petlist; }

    // Getter Methods

    public List<Pet> getPets() { return pets; }

    @Override
    public String toString() {
        return "PetList{ pets=" + pets + '}';
    }
}

