package servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.SQLDatabaseMaintenance_Ticket;
import dto.MaintenanceTicketTransfer;
import dto.MaintenanceTicketWrapper;
import models.Maintenance_Ticket;
import utils.PostgresConnectionUtil;


public class MaintenanceTicketServlet extends HttpServlet {



	// Jean Aldoph: Changed SQLDatabaseMaintenance_ticket ticket =
	// sqlDatabaseMaintenance_ticket.findByID(Integer);
	// to Maintenance_Ticket ticket = sqlDatabaseMaintenance_ticket.findByID(new
	// Integer(indexHeaderValue));
	Maintenance_Ticket maintenance_ticket = new Maintenance_Ticket();
	SQLDatabaseMaintenance_Ticket sqlDatabaseMaintenance_ticket = new SQLDatabaseMaintenance_Ticket(new PostgresConnectionUtil());
	private static Logger LOG = Logger.getLogger(MaintenanceTicketServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String getType = req.getHeader("find");
		if (getType == null) {
			resp.setStatus(400);
		} else if (getType.trim().equalsIgnoreCase("all")) {
			ObjectMapper om = new ObjectMapper();
			ArrayList<Maintenance_Ticket> tickets = sqlDatabaseMaintenance_ticket.findAll();
			if (tickets != null) {
				MaintenanceTicketWrapper ticketList = new MaintenanceTicketWrapper();
				ticketList.setTickets(tickets);
				String ticketsResponse = om.writeValueAsString(tickets);
				resp.getWriter().write(ticketsResponse);
				resp.setStatus(200);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
			} else {
				resp.setStatus(400);
			}
		} else if (getType.trim().equalsIgnoreCase("id")) {
			ObjectMapper om = new ObjectMapper();
			Integer id = Integer.parseInt(req.getHeader("id"));
			maintenance_ticket = sqlDatabaseMaintenance_ticket.findByID(id);
			if (maintenance_ticket != null) {
				String ticketsResponse = om.writeValueAsString(maintenance_ticket); 
				resp.getWriter().write(ticketsResponse);
				resp.setStatus(200);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
			} else {
				resp.setStatus(400);
			}
		} else if (getType.trim().equalsIgnoreCase("attraction")) {
			ObjectMapper om = new ObjectMapper();
			Integer id = Integer.parseInt(req.getHeader("id"));
			maintenance_ticket = sqlDatabaseMaintenance_ticket.findByAttraction(id);
			if (maintenance_ticket != null) {
				String ticketsResponse = om.writeValueAsString(maintenance_ticket); 
				resp.getWriter().write(ticketsResponse);
				resp.setStatus(200);
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
			} else {
				resp.setStatus(400);
			}
		} else {
			resp.setStatus(400);
		}
	}

        //Not implemented
        @Override
        protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doHead(req, resp);
        }

        /*
         * Requried JSON for addition
		{
 			"attractionId":[Integer value],
  			"employeeId": [Integer value],
			"status":[String value],
			"description":[String value]
		}
         */
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                if(req.getContentType().equals("application/json")) {
                        SQLDatabaseMaintenance_Ticket sqlDatabaseMaintenance_ticket = new SQLDatabaseMaintenance_Ticket(new PostgresConnectionUtil());
                        ObjectMapper om = new ObjectMapper();
                        MaintenanceTicketTransfer ticketData = om.readValue(req.getReader(), MaintenanceTicketTransfer.class);
                        int attractionId = ticketData.getAttractionId();
                        String status = ticketData.getStatus();
                        String description = ticketData.getDescription();
                        int employeeId = ticketData.getEmployeeId();
                        boolean isActive = ticketData.isActive();
                        Maintenance_Ticket newTicket = new Maintenance_Ticket(attractionId, employeeId, status, description, isActive);
                        if(!sqlDatabaseMaintenance_ticket.add(newTicket)) {
                                resp.setStatus(400);
                        } else {
                                resp.setStatus(201);
                        }
                } else {
                        resp.setStatus(400);
                }
        }

       /*
        *  Required JSON for update
        * {
        *	 "mainId":[Integer value],
        *	 "status":[String value],
   	 	*	 "endDate":[String value, format MM-dd-yyyy HH:mm]
        * }
        */
        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
                if(req.getContentType().equals("application/json")) {
                        ObjectMapper om = new ObjectMapper();
                        MaintenanceTicketTransfer ticketData = om.readValue(req.getReader(), MaintenanceTicketTransfer.class);
                        LocalDateTime endDate = LocalDateTime.parse(ticketData.getEndDate(), DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm"));
                        Maintenance_Ticket updateTicket = sqlDatabaseMaintenance_ticket.findByID(ticketData.getMainId());
                        if(updateTicket != null) {
                        	updateTicket.setEndDate(endDate);
                        	updateTicket.setStatus(ticketData.getStatus());
                            sqlDatabaseMaintenance_ticket.update(updateTicket.getMainId(), updateTicket);
                            resp.setStatus(204);
                        } else {
                            resp.setStatus(400);
                        }
                } else {
                        resp.setStatus(400);
                }
        }

        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doDelete(req, resp);
        }

        @Override
        public void destroy() {
                super.destroy();
        }

        @Override
        public void init() throws ServletException {
                super.init();
        }




}
