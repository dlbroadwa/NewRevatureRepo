import user.Customer;
import user.Employee;
import user.User;

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

        User c = new Customer("Simon", "Belmont", 12345);
        User e = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");

        users.add(c);
        users.add(e);
    }

    // Following Unit Tests meant to test Pet creation and interaction

    @Test
    public void testCustomerCreation() {

        User c = new Customer("Simon", "Belmont", 12345);
        assertEquals(12345, ((Customer) c).getID());

    }

    @Test
    public void testEmployeeCreation() {

        User e = new Employee("Bella", "Lugosi", 67890, "blugosi", "alucard");
        assertEquals(67890, ((Employee) e).getID());

    }

    @Test
    public void testForRecognizingUserType1() {

        // Grab the Customer from users, no Customer should be allowed to log into the database system
        User user1 = users.get(0);
        assertTrue(user1.userAuth("sbelmont", "vampirekiller") == false);

    }

    @Test
    public void testForRecognizingUserType2() {

        // Grab the Employee from users, Employees should be allowed to log into the database system with the
        // correct matching credentials
        User user1 = users.get(1);
        assertTrue(user1.userAuth("blugosi", "alucard") == true);

    }

    @Test
    public void testForRecognizingUserType3() {

        // Grab the Employee from users, Employees should be not be allowed to log into the database system with
        // incorrect credentials
        User user1 = users.get(1);
        assertTrue(user1.userAuth("sbelmont", "vampirekiller") == false);

    }
}
