import models.Cat;
import models.Dog;
import models.Pet;
import repos.Repository;
import services.PetService;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

public class PetTest {

    public PetTest() {}

    // Instance Variables
    // Initialize anything needed for mocking, storage, etc.
    ArrayList<Pet> pets;
    PetService petServ;

    @Mock
    Repository<Pet, Integer> repo; // Create mock of Repository to replace cWithSQL's DAO for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        pets = new ArrayList<Pet>();
        petServ =

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
