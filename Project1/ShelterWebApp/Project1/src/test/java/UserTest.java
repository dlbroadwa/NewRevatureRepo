import dev.models.user.Admin;
import dev.models.user.Customer;
import dev.models.user.Employee;
import dev.models.user.User;
import dev.repos.Repository;
import dev.services.UserService;

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

public class UserTest {

    public UserTest() {}

    // Instance Variables
    // Initialize anything needed for mocking, storage, etc.
    ArrayList<User> users = new ArrayList();
    UserService userServ;

    @Mock
    Repository<User, Integer> repo; // Create mock of Repository to replace petServ's repo for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and dev.services
        userServ = new UserService(repo);

        User c = new Customer("Simon", "Belmont", 12345, "sbelmont", "whiptime");
        User e = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");
        User a = new Admin("Vlad", "Tepes", 13579, "vtepes", "theimpaler");

        users.add(c);
        users.add(e);
        users.add(a);

        userServ.addNewUser(c);
        userServ.addNewUser(e);
        userServ.addNewUser(a);
    }

    // Following Unit Tests meant to test Pet creation and interaction

    @Test
    public void testCustomerCreation() {

        User c = new Customer("Simon", "Belmont", 12345, "sbelmont", "whiptime");
        assertEquals(12345, (c.getID()));

    }

    @Test
    public void testEmployeeCreation() {

        User e = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");
        assertEquals(67890, (e.getID()));

    }

    @Test
    public void testAdminCreation() {

        User a = new Employee("Vlad", "Tepes", 13579, "vtepes", "theimpaler");
        assertEquals(13579, (a.getID()));

    }

    @Test
    public void testForRecognizingUserType1() {

        // Grab the Customer from users
        User user1 = users.get(0);
        assertEquals("customer", user1.getUserType());

    }

    @Test
    public void testForRecognizingUserType2() {

        // Grab the Employee from users
        User user2 = users.get(1);
        assertEquals("employee", user2.getUserType());

    }

    @Test
    public void testForRecognizingUserType3() {

        // Grab the Admin from users
        User user3 = users.get(2);
        assertEquals("admin", user3.getUserType());

    }

    @Test
    public void testUser1Auth1() {

        // Grab the Employee from users, Users should provide the correct credentials to pass userAuth
        User user1 = users.get(1);
        assertTrue(user1.userAuth("blugosi", "alucard"));

    }

    @Test
    public void testUser1Auth2() {

        // Grab the Employee from users, Users should be rejected when passing incorrect credentials
        User user1 = users.get(1);
        assertTrue(user1.userAuth("sbelmont", "vampirekiller") == false);

    }

    @Test
    public void testUser2Auth1() {

        // Grab the Customer from users, Users should provide the correct credentials to pass userAuth
        User user2 = users.get(0);
        assertTrue(user2.userAuth("sbelmont", "whiptime"));

    }

    @Test
    public void testUser2Auth2() {

        // Grab the Customer from users, Users should be rejected when passing incorrect credentials
        User user2 = users.get(0);
        assertTrue(user2.userAuth("sbelmont", "vampirekiller") == false);

    }

    @Test
    public void testUser3Auth1() {

        // Grab the Admin from users, Users should provide the correct credentials to pass userAuth
        User user3 = users.get(2);
        assertTrue(user3.userAuth("vtepes", "theimpaler"));

    }

    @Test
    public void testUser3Auth2() {

        // Grab the Admin from users, Users should be rejected when passing incorrect credentials
        User user3 = users.get(2);
        assertTrue(user3.userAuth("sbelmont", "vampirekiller") == false);

    }

    @Test
    public void testUserPrintBaseInfo1() {

        User user1 = users.get(1);
        assertEquals(" ID # 67890 | Name: Bella Lugosi", user1.printBaseInfo());

    }

    // The following Unit Tests are meant for checking database-involved functionalities through
    // a mocked Repository<User,Integer> instance to avoid using the existing postgresql db.

    @Test
    public void shouldReturnSameUserList() {

        // Mock Repository should return the same pet list
        Mockito.when(repo.findAll()).thenReturn(users);
        ArrayList<User> actual = userServ.getUserSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected User entries", users.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameUserListAfterAdding1() {

        // Mock Repository should return the same user list after having been given a user
        User e1 = new Employee("Doug", "Nosferatu", 551998, "dnosferatu", "masquerade");

        users.add(e1);
        userServ.addNewUser(e1);

        Mockito.when(repo.findAll()).thenReturn(users);
        ArrayList<User> actual = userServ.getUserSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected User entries", users.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameUserListAfterAdding2() {

        // Mock Repository should return the same user list after having been given another user
        User c1 = new Customer("Vance", "Helsing", 11358, "vhelsing", "diemonster");

        users.add(c1);
        userServ.addNewUser(c1);

        Mockito.when(repo.findAll()).thenReturn(users);
        ArrayList<User> actual = userServ.getUserSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected User entries", users.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameUserListAfterRemoving1() {

        // Mock Repository should return the same item list after having been given a user to remove
        User c1 = new Customer("Vance", "Helsing", 11358, "vhelsing", "diemonster");

        users.remove(c1);
        userServ.removeUser(11358);

        Mockito.when(repo.findAll()).thenReturn(users);
        ArrayList<User> actual = userServ.getUserSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected User entries", users.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameUserListAfterUpdating1() {

        // Mock Repository should return the same item list after having been given a user to update
        User e2 = new Employee("Bart", "Martinon", 551998, "bmartino", "project1");

        users.set(2,e2);
        userServ.updateUser(e2, 551998);

        Mockito.when(repo.findAll()).thenReturn(users);
        ArrayList<User> actual = userServ.getUserSQLRepo().findAll();
        Assert.assertArrayEquals("Did not return expected User entries", users.toArray(), actual.toArray());

    }

    @Test
    public void shouldReturnSameUserAfterSearching1() {

        // Mock Repository should return the same item that's been search for
        User e2 = new Employee("Bart", "Martinon", 551998, "bmartino", "project1");
        int targetID = 551998;

        User mockResult = null;
        for ( User u : users ) {
            if ( u.getID() == targetID ) {
                mockResult = u;
            }
        }

        Mockito.when(repo.findById(551998)).thenReturn(mockResult);
        User actual = userServ.getUserSQLRepo().findById(551998);
        Assert.assertEquals("Did not return expected User entries", mockResult, actual);

    }
}
