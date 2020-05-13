package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.TicketDAO;
import models.Ticket;
import utils.PostgresConnectionUtil;

/**
 * Servlet implementation class TicketServlet
 * 
 * @author Joshua Brewer
 */
public class TicketServlet extends HttpServlet {
	private PostgresConnectionUtil connection = new PostgresConnectionUtil();
	private TicketDAO ticketDao = new TicketDAO(connection);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String indexHeaderValue = req.getHeader("searchIndex");
		if(indexHeaderValue == null) {
			resp.setStatus(400);
		} else {
			ObjectMapper om = new ObjectMapper();
			int searchIndex = Integer.parseInt(indexHeaderValue);
			if(searchIndex <= 0) {
				ArrayList<Ticket> tickets = ticketDao.findAll();
				if(tickets != null) {
					String ticketsResponse = om.writeValueAsString(tickets); //to be replaced with wrapper class later
					resp.getWriter().write(ticketsResponse);
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
				} else {
					resp.setStatus(400);
				}
			} else {
				Ticket ticket = ticketDao.findById(searchIndex);
				if(ticket != null) {
					String ticketResponse = om.writeValueAsString(ticket);
					resp.getWriter().write(ticketResponse);
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
				} else {
					resp.setStatus(400);
				}
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getContentType().equals("application/json")) {
			ObjectMapper om = new ObjectMapper();
			Ticket newTicket = om.readValue(req.getReader(), Ticket.class);
			if(ticketDao.save(newTicket) <= 0) {
				resp.setStatus(400);
			} else {
				resp.setStatus(201);
			}
		} else {
			resp.setStatus(400);
		}
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getContentType().equals("application/json")) {
			ObjectMapper om = new ObjectMapper();
			Ticket updateTicket = om.readValue(req.getReader(), Ticket.class);
			if(ticketDao.findById(updateTicket.getTicketID()) != null) {
				ticketDao.update(updateTicket, updateTicket.getTicketID());
				resp.setStatus(204);
			} else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(400);
		}
	}


}
