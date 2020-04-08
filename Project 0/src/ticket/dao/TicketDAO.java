package ticket.dao;

import java.util.List;
import ticket.Ticket;

public interface TicketDAO {
	
	List<Ticket> getAllTickets();
	List<Ticket> getTicketsByUser(String user);
	
	boolean addTicket(Ticket ticket);
	boolean updateTicket(Ticket ticket);
	boolean deleteTicket(Ticket ticket);
}
