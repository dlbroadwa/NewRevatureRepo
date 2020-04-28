import models.Cat;
import models.Dog;
import models.Pet;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PetTest {

    public PetTest() {}

    // Instance Variables
    // Initialize anything needed for mocking, storage, etc.
    ArrayList<Pet> pets;

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        pets = new ArrayList<Pet>();

        Pet d = new Dog(123, "Buddy", "GermanShepard", "M", 6);
        Pet c = new Cat(654, "Sandy", "Abyssinian", "F", 3);

        pets.add(d);
        pets.add(c);
    }

    // Following Unit Tests meant to test Pet creation and interaction

    @Test
    public void testDogCreation() {

        Pet d = new Dog(123, "Buddy", "GermanShepard", "M", 6);
        assertEquals(123, d.getID());

    }

    @Test
    public void testCatCreation() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "F", 3);
        assertEquals(654, c.getID());

    }

    @Test
    public void testForRecognizingPetType1() {

        // Grab the Dog from pets
        Pet pet1 = pets.get(0);
        assertEquals("Dog", pet1.getPetType());

    }

    @Test
    public void testForRecognizingPetType2() {

        // Grab the Cat from pets
        Pet pet2 = pets.get(1);
        assertEquals("Cat", pet2.getPetType());

    }
}
