import data.DAO;
import data.SqlDAO;
import models.Dictionary;
import models.Item;
import models.Novel;
import repos.Repository;
import services.Catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;

public class LibraryTest {

    public LibraryTest() {}

    // Initialize Catalog and ArrayList<Item> instances for unit testing involving the database
    // through the Mockito service.
    Catalog cWithSQL;
    DAO dao;
    ArrayList<Item> items = new ArrayList();

    @Mock
    Repository<Item, Integer> repo; // Create mock of Repository to replace cWithSQL's DAO for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        dao = new SqlDAO(repo);
        cWithSQL = new Catalog(dao);

        Item d = new Dictionary(1123, true, "VivaEspana", "SonyaDos", "WorldSpeaker",
                2004, "Spanish", 6543);
        items.add(d);
        cWithSQL.addNewBook(d);
    }

    @Test
    public void shouldReturnSameItemList() {

        // DAO with Mock Repository should return the same item list
        Mockito.when(dao.getContent()).thenReturn(items);
        ArrayList<Item> actual = cWithSQL.getItemList();
        Assert.assertArrayEquals("Did not return expected Item entries", items.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameItemListAfterAdding1() {

        // DAO with Mock Repository should return the same item list after having been given an item
        Item d1 = new Dictionary(888888, true, "FromRussiaWithLessons", "ZaryaRasputin",
                "WorldSpeaker", 2007, "Russian", 7654);

        items.add(d1);
        cWithSQL.addNewBook(d1);

        Mockito.when(dao.getContent()).thenReturn(items);
        ArrayList<Item> actual = cWithSQL.getItemList();
        Assert.assertArrayEquals("Did not return expected Item entries", items.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameItemListAfterAdding2() {

        // DAO with Mock Repository should return the same item list after having been given another item
        Item n = new Novel(12357, true, "TomorrowWorld", "AlfonseUno", "BeyondPages",
                2016, "Sci-Fi");

        items.add(n);
        cWithSQL.addNewBook(n);

        Mockito.when(dao.getContent()).thenReturn(items);
        ArrayList<Item> actual = cWithSQL.getItemList();
        Assert.assertArrayEquals("Did not return expected Item entries", items.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameItemListAfterRemoving1() {

        // DAO with Mock Repository should return the same item list after having an item removed
        Item n = new Novel(12357, true, "TomorrowWorld", "AlfonseUno", "BeyondPages",
                2016, "Sci-Fi");

        items.remove(n);
        cWithSQL.removeBook(12357);

        Mockito.when(dao.getContent()).thenReturn(items);
        ArrayList<Item> actual = cWithSQL.getItemList();
        Assert.assertArrayEquals("Did not return expected Item entries", items.toArray(), actual.toArray());

    }

    // Following Unit Tests meant to test object creation and interaction using the old FileIO system

    @Test
    public void testDictionaryCreation() {

        Item d = new Dictionary(12345, true, "TheBigBookOfWords", "ProfessorWright",
                "WordsmithInc.", 2015, "English", 9876);
        assertEquals(12345, d.getID());

    }

    @Test
    public void testNovelCreation() {

        Item n = new Novel(67890, true, "TheGreatAdventure", "ArthurEnglish",
                "StorytimeLLC", 2009, "Drama");
        assertEquals(67890, n.getID());

    }

    @Test
    public void testCatalogCreation() {

        Catalog c = new Catalog();
        assertEquals(4, (c.getItemList()).size());

    }

    // Following Unit Tests are meant to test Catalog methods

    @Test
    public void testSearchByID1() {

        // An item with that ID is found in Catalog
        Item target = new Novel(67890, true, "TheGreatAdventure", "ArthurEnglish",
                "StorytimeLLC", 2009, "Drama"); // Item isntance we're trying to return

        Catalog c = new Catalog();
        Item b = c.searchByID(67890);
        assertEquals(target.getID(), b.getID()); // Matching IDs implies success

    }

    @Test
    public void testSearchByID2() {

        // An item with that ID is not found in Catalog

        Catalog c = new Catalog();
        Item b = c.searchByID(99999);
        assertNull(b); // Null implies failure

    }

    @Test
    public void testAddNewBook1() {

        // New Book instance we're trying to add into the catalog
        Item n1 = new Novel(12357, true, "TomorrowWorld", "AlfonseUno", "BeyondPages",
                2016, "Sci-Fi");

        Catalog c = new Catalog();
        c.addNewBook(n1);

        // New book should show up when searching
        Item b = c.searchByID(12357);
        assertEquals(n1.getID(), b.getID()); // Matching IDs implies success

    }

    @Test
    public void testAddNewBook2() {

        // New Book instance we're trying to add into the catalog
        Item n1 = new Novel(12357, true, "TomorrowWorld", "AlfonseUno", "BeyondPages",
                2016, "Sci-Fi");

        Catalog c = new Catalog();
        c.addNewBook(n1);

        // Let's add another new Book using the same initial Book instance
        // Simulate when a new book with a non-unique ID is being added into the system.

        Item n2 = new Novel(12357, true, "TomorrowWorld", "AlfonseUno", "BeyondPages",
                2016, "Sci-Fi");
        c.addNewBook(n2);

        // System message should show to tell unused.user that the process was not performed, and the current amount of books
        // should be still 5.
        assertEquals(5, (c.getItemList()).size());
    }

    @Test
    public void testRemoveBook() {

        Catalog c = new Catalog();

        // Remove one of the already existing books
        c.removeBook(12345);
        Item b = c.searchByID(12345);
        assertNull(b); // Null implies the book no longer exists
    }

    @Test
    public void testCheckOut() {

        Catalog c = new Catalog();

        // Check out one of the already existing books
        c.checkOut(67890);
        assertTrue((c.searchByID(67890)).getCheckStatus() == false);
        // Book we checked out should have a checkStatus of false.
    }

    @Test
    public void testCheckIn() {

        Catalog c = new Catalog();

        // Check out one of the already existing books
        c.checkOut(67890);

        // Check it back in
        c.checkIn(67890);

        assertTrue((c.searchByID(67890)).getCheckStatus() == true);
        // Book we checked back in should have a checkStatus of true.
    }


}