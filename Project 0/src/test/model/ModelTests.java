package test.model;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import ticket.model.Post;
import ticket.model.Ticket;
import ticket.model.User;

public class ModelTests {

	Post postInstance;
	Post postInstance2;
	Ticket ticketInstance;
	Ticket ticketInstance2;
	User userInstance;
	LocalDateTime timeNow1;
	LocalDateTime timeNow2;
	
	@Before
	public void setup() {
		timeNow1 = LocalDateTime.now();
		timeNow2 = LocalDateTime.now();
		postInstance = new Post(1, "user_id1", 1, "body1", timeNow1);
		postInstance2 = new Post(2, "user_id2", 2, "body2");
		ticketInstance = new Ticket(1, "user_id1", "title1", "status", "priority", timeNow1, timeNow2);
		ticketInstance2 = new Ticket(2, "user_id2", "title2");
		userInstance = new User("user_id", "password", "first_name", "last_name", "email", false);
	}
	
// Post Tests	
	
	@Test
	public void testPostConstructor() {
		assertNotNull(postInstance);
		assertNotNull(postInstance2);
	}
	
	@Test
	public void testPostGetTicketId() {
		assertEquals(1, postInstance.getTicketId());
		assertEquals(2, postInstance2.getTicketId());
	}
	
	@Test
	public void testPostGetPosterId() {
		assertEquals("user_id1", postInstance.getPosterId());
		assertEquals("user_id2", postInstance2.getPosterId());
	}
	
	@Test
	public void testPostGetPostOrder() {
		assertEquals(1, postInstance.getPostOrder());
		assertEquals(2, postInstance2.getPostOrder());
	}
	
	@Test
	public void testPostGetBody() {
		assertEquals("body1", postInstance.getBody());
		assertEquals("body2", postInstance2.getBody());
	}

	@Test
	public void testPostGetCreationDate() {
		assertEquals(timeNow1, postInstance.getCreationDate());
		assertNotNull(postInstance2.getCreationDate());
	}
	
// Ticket Tests
	
	@Test
	public void testTicketConstructor() {
		assertNotNull(ticketInstance);
		assertNotNull(ticketInstance2);
	}
	
	@Test
	public void testTicketGetTicketId() {
		assertEquals(1, ticketInstance.getTicketId());
		assertEquals(2, ticketInstance2.getTicketId());
	}
	
	@Test
	public void testTicketGetUserId() {
		assertEquals("user_id1", ticketInstance.getUserId());
		assertEquals("user_id2", ticketInstance2.getUserId());
	}
	
	@Test
	public void testTicketGetTitle() {
		assertEquals("title1", ticketInstance.getTitle());
		assertEquals("title2", ticketInstance2.getTitle());
	}
	
	@Test
	public void testTicketGetStatus() {
		assertEquals("status", ticketInstance.getStatus());
		assertEquals(Ticket.STATUS_OPEN, ticketInstance2.getStatus());
	}
	
	@Test
	public void testTicketGetPriority() {
		assertEquals("priority", ticketInstance.getPriority());
		assertEquals(Ticket.PRIORITY_LOW, ticketInstance2.getPriority());
	}
	
	@Test
	public void testTicketGetCreationDate() {
		assertEquals(timeNow1, ticketInstance.getCreationDate());
		assertNotNull(ticketInstance2.getCreationDate());
	}
	
	@Test
	public void testTicketGetLastUpdated() {
		assertEquals(timeNow2, ticketInstance.getLastUpdated());
		assertNotNull(ticketInstance2.getLastUpdated());
	}
	
	@Test
	public void testTicketSetStatus() {
		String status = "newStatus";
		ticketInstance.setStatus(status);
		assertEquals(status, ticketInstance.getStatus());
	}
	
	@Test
	public void testTicketSetPriority() {
		String priority = "newPriority";
		ticketInstance.setPriority(priority);
		assertEquals(priority, ticketInstance.getPriority());
	}
	
	@Test
	public void testTicketSetLastUpdated() {
		LocalDateTime localDate = LocalDateTime.now();
		ticketInstance.setLastUpdated(localDate);
		assertEquals(localDate, ticketInstance.getLastUpdated());
	}
	
// User Tests
	
	@Test
	public void testUserConstructor() {
		assertNotNull(userInstance);
	}
	
	@Test
	public void testUserGetId() {
		assertEquals("user_id", userInstance.getId());
	}
	
	@Test
	public void testUserGetPassword() {
		assertEquals("password", userInstance.getPassword());
	}
	
	@Test
	public void testUserGetFirstName() {
		assertEquals("first_name", userInstance.getFirstName());
	}
	
	@Test
	public void testUserGetLastName() {
		assertEquals("last_name", userInstance.getLastName());
	}
	
	@Test
	public void testUserGetEmail() {
		assertEquals("email", userInstance.getEmail());
	}
	
	@Test
	public void testUserIsAdmin() {
		assertEquals(false, userInstance.isAdmin());
	}
	
	@Test
	public void testUserSetPassword() {
		String password = "newPassword";
		userInstance.setPassword(password);
		assertEquals(password, userInstance.getPassword());
	}
	
	@Test
	public void testUserSetFirstName() {
		String first_name = "newFirstName";
		userInstance.setFirstName(first_name);
		assertEquals(first_name, userInstance.getFirstName());
	}
	
	@Test
	public void testUserSetLastName() {
		String last_name = "newLastName";
		userInstance.setLastName(last_name);
		assertEquals(last_name, userInstance.getLastName());
	}
	
	@Test
	public void testUserSetEmail() {
		String email = "newEmail";
		userInstance.setEmail(email);
		assertEquals(email, userInstance.getEmail());
	}
	
	@Test
	public void testUserSetAdmin() {
		boolean admin = true;
		userInstance.setAdmin(admin);
		assertEquals(admin, userInstance.isAdmin());
	}
}
