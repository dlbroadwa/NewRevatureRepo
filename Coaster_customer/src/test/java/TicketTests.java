


import models.Ticket;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class TicketTests {
    // Instance Variables
    // Initialized anything needed for mocking, storage, etc.
    ArrayList<Ticket> tickets = new ArrayList();

    @Before
    public void init() {
        // TODO Put initial content for jUnit tests, establish mocked dependencies and services
        Ticket t = new Ticket(123, 5, 1, LocalDateTime.now(), LocalDateTime.now());

        tickets.add(t);
    }

    // The following section of Unit Tests meant to test Ticket creation and interaction

    @Test
    public void testTicketCreation() {

        Ticket t = new Ticket(123, 5, 1, LocalDateTime.now(), LocalDateTime.now());
        String output = "" + t.getTicketID() + " " + t.getCustomerID() + " " + t.getAccessLevel() + " " + t.getStartDate() + " " + t.getEndDate();
        assertEquals("123 5 1 "+ new Date() + " " + new Date(), output);

    }


}
