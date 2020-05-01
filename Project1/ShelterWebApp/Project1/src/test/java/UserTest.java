import models.user.Admin;
import models.user.Customer;
import models.user.Employee;
import models.user.User;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UserTest {

    public UserTest() {}

    // Instance Variables
    // Initialize anything needed for mocking, storage, etc.
    ArrayList<User> users;

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        users = new ArrayList<User>();

        User c = new Customer("Simon", "Belmont", 12345, "sbelmont", "whiptime");
        User e = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");
        User a = new Admin("Vlad", "Tepes", 13579, "vtepes", "theimpaler");

        users.add(c);
        users.add(e);
        users.add(a);
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
    public void testUserAuth1() {

        // Grab the Employee from users, Users should provide the correct credentials to pass userAuth
        User user1 = users.get(1);
        assertTrue(user1.userAuth("blugosi", "alucard"));

    }

    @Test
    public void testUserAuth2() {

        // Grab the Employee from users, Users should be rejected when passing incorrect credentials
        User user1 = users.get(1);
        assertTrue(user1.userAuth("sbelmont", "vampirekiller") == false);

    }
}
