package models;
import models.Customer;
import models.Ticket;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CustomerTests {
    // Instance Variables
    // Initialized anything needed for mocking, storage, etc.
    ArrayList<Customer> customers = new ArrayList();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        Customer c = new Customer(123, "John", "Doe", "johndoe@emailprovider.com");

        customers.add(c);
    }

    // The following section of Unit Tests meant to test Ticket creation and interaction

    @Test
    public void testCustomerCreation() {

        Customer c = new Customer(123, "John", "Doe", "johndoe@emailprovider.com");
        String output = "" + c.getCustomerID() + " " + c.getFirstname() + " " + c.getLastname() + " " + c.getEmail();
        assertEquals("123 John Doe johndoe@emailprovider.com", output);

    }


}
