package models.pet;

import java.util.ArrayList;
import java.util.List;

public class PetList {
    public List<Pet> pets;

    public PetList() { pets = new ArrayList<>(); }

    public PetList(ArrayList<Pet> petlist) { pets = petlist; }

    @Override
    public String toString() {
        return "PetList{" +
                "pets=" + pets +
                '}';
    }
}

