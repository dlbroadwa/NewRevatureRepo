import dao.DAO;
import models.Customer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CustomerTests {
    // Instance Variables
    // Initialized anything needed for mocking, storage, etc.
    ArrayList<Customer> customers = new ArrayList();

    @Mock
    DAO<Customer, String> customerDAO; // Create mock of DAO to replace repo for unit testing

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        Customer c = new Customer(123, "John", "Doe", "johndoe@emailprovider.com", "thisisabadpassword");
        customers.add(c);

        customerDAO.save(c);
    }

    // The following section of Unit Tests meant to test Ticket creation and interaction

    @Test
    public void testCustomerCreation() {

        Customer c = new Customer(123, "John", "Doe", "johndoe@emailprovider.com", "thisisabadpassword");
        String output = "" + c.getCustomerID() + " " + c.getFirstname() + " " + c.getLastname() + " " + c.getEmail() + " " + c.getPassword();
        assertEquals("123 John Doe johndoe@emailprovider.com thisisabadpassword", output);

    }

    // The following Unit Tests are meant for checking database-involved functionalities through
    // a mocked DAO<Customer,Integer> instance to avoid using the existing postgresql db.

    @Test
    public void shouldReturnSameUserList() {

        // Mock Repository should return the same pet list
        Mockito.when(customerDAO.findAll()).thenReturn(customers);
        ArrayList<Customer> actual = customerDAO.findAll();
        Assert.assertArrayEquals("Did not return expected Customer entries", customers.toArray(), actual.toArray());

    }



}
