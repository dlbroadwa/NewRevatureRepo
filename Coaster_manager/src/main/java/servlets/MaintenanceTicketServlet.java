package servlets;

import data.SQLDatabaseMaintenance_Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import java.io.IOException;



@WebServlet
public class MaintenanceTicketServlet extends HttpServlet {

        SQLDatabaseMaintenance_Ticket sqlDatabaseMaintenance_ticket;
        private static Logger LOG = Logger.getLogger(MaintenanceTicketServlet.class);
        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doGet(req, resp);
        }

        @Override
        protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doHead(req, resp);
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doPost(req, resp);
        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                super.doPut(req, resp);
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
