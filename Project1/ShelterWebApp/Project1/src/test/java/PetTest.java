import models.pet.Cat;
import models.pet.Dog;
import models.pet.Pet;
import org.junit.Assert;
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
    ArrayList<Pet> pets = new ArrayList();
    PetService petServ;

    @Mock
    Repository<Pet, Integer> repo; // Create mock of Repository to replace cWithSQL's DAO for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        petServ = new PetService(repo);

        Pet d = new Dog(123, "Buddy", "GermShep", "M", 6);
        Pet c = new Cat(654, "Sandy", "Abyssinian", "F", 3);

        pets.add(d);
        pets.add(c);

        petServ.addNewPet(d);
        petServ.addNewPet(c);
    }

    // Following Unit Tests meant to test Pet creation and interaction

    @Test
    public void testDogCreation() {

        Pet d = new Dog(123, "Buddy", "GermShep", "m", 6);
        assertEquals(123, d.getID());

    }

    @Test
    public void testCatCreation() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "f", 3);
        assertEquals(654, c.getID());

    }

    @Test
    public void testForRecognizingPetType1() {

        // Grab the Dog from pets
        Pet pet1 = pets.get(0);
        assertEquals("dog", pet1.getPetType());

    }

    @Test
    public void testForRecognizingPetType2() {

        // Grab the Cat from pets
        Pet pet2 = pets.get(1);
        assertEquals("cat", pet2.getPetType());

    }

    // The following Unit Tests are meant for checking database-involved functionalities through
    // a mocked Repository<Pet,Integer> instance to avoid using the existing postgresql db.

    @Test
    public void shouldReturnSamePetList() {

        // Mock Repository should return the same pet list
        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSamePetListAfterAdding1() {

        // Mock Repository should return the same item list after having been given a pet
        Pet c1 = new Cat(1357, "Whiskers", "Tabby", "m", 2);

        pets.add(c1);
        petServ.addNewPet(c1);

        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSamePetListAfterAdding2() {

        // Mock Repository should return the same item list after having been given a pet
        Pet d1 = new Dog(2468, "Danger", "MiniSchnau", "m", 8);

        pets.add(d1);
        petServ.addNewPet(d1);

        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSamePetListAfterRemoving1() {

        // Mock Repository should return the same item list after having been given a pet
        Pet d1 = new Dog(2468, "Danger", "MiniSchnau", "m", 8);

        pets.remove(d1);
        petServ.removePet(2468);

        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }
}
