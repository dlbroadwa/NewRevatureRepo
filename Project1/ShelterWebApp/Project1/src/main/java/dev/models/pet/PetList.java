package dev.models.pet;

import java.util.ArrayList;
import java.util.List;

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

