package ticket.dao;

import java.util.List;

import ticket.model.Ticket;

/**
 * TicketDAO --- Accesses data from a tickets table.
 * @author Austin Kind
 */
public interface TicketDAO {
	
	/**
	 * Retrieves all the tickets from the tickets table and returns them in a list.
	 * @return 			A list of all Tickets.
	 */
	List<Ticket> getAllTickets();
	
	/**
	 * Retrieves all the tickets from the given user ID and returns them in a list.
	 * @param user		The user ID of the tickets.
	 * @return 			A list of Tickets from the given user ID.
	 */
	List<Ticket> getTicketsByUser(String user);
	
	/**
	 * Returns the highest ticket ID plus one.
	 * @return 		The next highest ticket ID.
	 */
	int getNextTicketId();
	
	/**
	 * Adds the given Ticket to the table.
	 * @param ticket	The ticket to be added.
	 * @return 			True if operation was successful.
	 */
	boolean addTicket(Ticket ticket);
	
	/**
	 * Updates the given Ticket to the table.
	 * @param ticket	The ticket to be updated.
	 * @return 			True if operation was successful.
	 */
	boolean updateTicket(Ticket ticket);
	
	/**
	 * Deletes the given Ticket from the table.
	 * @param ticket	The ticket to be deleted.
	 * @return 			True if operation was successful.
	 */
	boolean deleteTicket(Ticket ticket);
}
