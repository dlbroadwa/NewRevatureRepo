package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.SQLDatabaseMaintenance_Ticket;
import models.Maintenance_Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.Logger;
import java.util.List;




public class MaintenanceTicketServlet extends HttpServlet {



        //Jean Aldoph: Changed SQLDatabaseMaintenance_ticket ticket = sqlDatabaseMaintenance_ticket.findByID(Integer);
        // to Maintenance_Ticket ticket = sqlDatabaseMaintenance_ticket.findByID(new Integer(indexHeaderValue));
        Maintenance_Ticket maintenance_ticket = new Maintenance_Ticket();
        SQLDatabaseMaintenance_Ticket sqlDatabaseMaintenance_ticket;
        private static Logger LOG = Logger.getLogger(MaintenanceTicketServlet.class);
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                String indexHeaderValue = req.getHeader("searchIndex");
                if(indexHeaderValue == null) {
                        resp.setStatus(400);
                } else {
                        ObjectMapper om = new ObjectMapper();
                        int searchIndex = Integer.parseInt(indexHeaderValue);
                        if(searchIndex <= 0) {
                                List<Maintenance_Ticket> tickets = sqlDatabaseMaintenance_ticket.findAll();
                                if(maintenance_ticket != null) {
                                        String ticketsResponse = om.writeValueAsString(tickets); //to be replaced with wrapper class later
                                        resp.getWriter().write(ticketsResponse);
                                        resp.setStatus(200);
                                        resp.setContentType("application/json");
                                        resp.setCharacterEncoding("UTF-8");
                                } else if(req.getHeader("find").equals("attraction")){
                                        Integer id = new Integer(req.getHeader("id"));
                                        sqlDatabaseMaintenance_ticket.findByAttraction(id);
                                        if(maintenance_ticket != null) {
                                                String ticketsResponse = om.writeValueAsString(tickets); //to be replaced with wrapper class later
                                                resp.getWriter().write(ticketsResponse);
                                                resp.setStatus(200);
                                                resp.setContentType("application/json");
                                                resp.setCharacterEncoding("UTF-8");
                                }

                        }else{
                                        resp.setStatus(400);
                                }
                        }
                }

        }

        //Not implemented
        @Override
        protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doHead(req, resp);
        }


        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

                if(req.getContentType().equals("application/json")) {
                        ObjectMapper om = new ObjectMapper();
                        Maintenance_Ticket newTicket = om.readValue(req.getReader(), Maintenance_Ticket.class);
                        if(!sqlDatabaseMaintenance_ticket.add(newTicket)) {
                                resp.setStatus(400);
                        } else {
                                resp.setStatus(201);
                        }
                } else {
                        resp.setStatus(400);
                }
        }


        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
        {
                if(req.getContentType().equals("application/json")) {
                        ObjectMapper om = new ObjectMapper();
                        Maintenance_Ticket updateTicket = om.readValue(req.getReader(), Maintenance_Ticket.class);
                        if(sqlDatabaseMaintenance_ticket.findByID(updateTicket.getMainId()) != null) {
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