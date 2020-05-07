import dev.models.pet.Cat;
import dev.models.pet.Dog;
import dev.models.pet.Pet;
import dev.models.pet.PetList;
import dev.repos.Repository;
import dev.services.PetService;

import org.junit.Assert;
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
    Repository<Pet, Integer> repo; // Create mock of Repository to replace petServ's repo for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and dev.services
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
    public void testPetListWrapperCreation() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "f", 3);
        Pet d = new Dog(123, "Buddy", "GermShep", "m", 6);

        ArrayList<Pet> petList = new ArrayList();
        petList.add(c);
        petList.add(d);

        PetList petListWrapper = new PetList(petList);
        assertEquals(petList, petListWrapper.getPets());
    }

    @Test
    public void testPetListWrapperToString() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "f", 3);
        Pet d = new Dog(123, "Buddy", "GermShep", "m", 6);

        ArrayList<Pet> petList = new ArrayList();
        petList.add(c);
        petList.add(d);

        PetList petListWrapper = new PetList(petList);
        assertEquals("PetList{ pets=" + petListWrapper.getPets() + '}', petListWrapper.toString());
    }

    @Test
    public void testPetPrintBaseInfo1() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "f", 3);
        assertEquals(" ID # 654 | Name: Sandy | Breed: Abyssinian | Gender: f | Age: 3", c.printBaseInfo());

    }

    @Test
    public void testPetSubclassPrintInfo1() {

        Pet c = new Cat(654, "Sandy", "Abyssinian", "f", 3);
        assertEquals("cat:  ID # 654 | Name: Sandy | Breed: Abyssinian | Gender: f | Age: 3", c.printInfo());

    }

    @Test
    public void testPetSubclassPrintInfo2() {

        Pet d = new Dog(123, "Buddy", "GermShep", "m", 6);
        assertEquals("dog:  ID # 123 | Name: Buddy | Breed: GermShep | Gender: m | Age: 6", d.printInfo());

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

        // Mock Repository should return the same item list after having a pet removed
        Pet d1 = new Dog(2468, "Danger", "MiniSchnau", "m", 8);

        pets.remove(d1);
        petServ.removePet(2468);

        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSamePetListAfterUpdating1() {

        // Mock Repository should return the same item list after a pet has been updated
        Pet c2 = new Cat(1357, "Nyx", "Bengal", "f", 1);

        pets.set(0,c2);
        petServ.updatePet(c2,1357);

        Mockito.when(repo.findAll()).thenReturn(pets);
        ArrayList<Pet> actual = petServ.getPetSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected Pet entries", pets.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSamePetAfterSearching1() {

        // Mock Repository should return the same item that's been search for
        Pet c2 = new Cat(1357, "Whiskers", "Tabby", "m", 2);
        int targetID = 1357;

        Pet mockResult = null;
        for ( Pet p : pets ) {
            if ( p.getID() == targetID ) {
                mockResult = p;
            }
        }

        Mockito.when(repo.findById(1357)).thenReturn(mockResult);
        Pet actual = petServ.getPetSQLRepo().findById(1357);
        Assert.assertEquals("Did not return expected Pet entries", mockResult, actual);

    }
}
