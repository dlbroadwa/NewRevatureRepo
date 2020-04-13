package ticket.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import ticket.model.Ticket;
import ticket.model.User;

public class TicketDAOFileIO implements TicketDAO {
	
	final String TICKETS_PATH = System.getProperty("user.dir") + File.separator + "tickets.txt";
	BufferedReader buffer;
	BufferedWriter writer;
	
	public List<Ticket> getAllTickets() {
		List<Ticket> list = new ArrayList<>();
		
		try {
			String currentEntry;
			buffer = new BufferedReader(new FileReader(TICKETS_PATH));
			while ((currentEntry = buffer.readLine()) != null) {
				String[] ticket = currentEntry.split("\\s+");
				list.add(new Ticket(Integer.parseInt(ticket[0]), ticket[1], ticket[2], ticket[3], ticket[4], ticket[5], LocalDateTime.parse(ticket[6])));
			}
			return list;
		} catch (FileNotFoundException e) {
			File tickets = new File(TICKETS_PATH);
			try {
				tickets.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public List<Ticket> getTicketsByUser(String user) {
		List<Ticket> list = new ArrayList<>();
		
		try {
			String currentEntry;
			buffer = new BufferedReader(new FileReader(TICKETS_PATH));
			while ((currentEntry = buffer.readLine()) != null) {
				String[] ticket = currentEntry.split("\\s+");
				if (ticket[1].equals(user)) {
					list.add(new Ticket(Integer.parseInt(ticket[0]), ticket[1], ticket[2], ticket[3], ticket[4], ticket[5], LocalDateTime.parse(ticket[6])));
				}
			}
			return list;
		} catch (FileNotFoundException e) {
			File tickets = new File(TICKETS_PATH);
			try {
				tickets.createNewFile();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buffer != null)
					buffer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean addTicket(Ticket ticket) {
		boolean result = false;
		try {
			writer = new BufferedWriter(new FileWriter(TICKETS_PATH));
			writer.write(ticket.getTicketId() + " " + ticket.getUserId() + " " + ticket.getTitle() + " " + ticket.getBody());
			writer.newLine();
			result = true;
		} catch (FileNotFoundException e) {
			File users = new File(TICKETS_PATH);
			try {
				users.createNewFile();
				addTicket(ticket);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null)
					writer.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	//TODO
	public boolean updateTicket(Ticket ticket) {
		return true;
	}
	
	//TODO
	public boolean deleteTicket(Ticket ticket) {
		return true;
	}
}
