package servlets;

import data.SQLDatabaseMaintenance_Ticket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import utils.ConnectionUtil;

import java.io.IOException;
import java.sql.SQLException;


@WebServlet
public class MaintenanceTicketServlet extends HttpServlet {

        private final SQLDatabaseMaintenance_Ticket sqlDatabaseMaintenanceTicket = new SQLDatabaseMaintenance_Ticket(ConnectionUtil);
        private static Logger LOG = Logger.getLogger(MaintenanceTicketServlet.class);

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response){
                HttpSession session = request.getSession();
                String action = request.getServletPath();


        }
        protected void doPost(HttpServletRequest request, HttpServletResponse response) {
                HttpSession session = request.getSession();
                String action = request.getServletPath();

        }





        private void createAction(HttpServletRequest req, HttpServletResponse resp)
                throws SQLException, IOException, ServletException {
                // TODO Follow Jean's example regarding JSON and Gson.

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
        }

        private void readAction(HttpServletRequest req, HttpServletResponse resp)
                throws SQLException, IOException, ServletException {
                // TODO Follow Jean's example regarding JSON and Gson.

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
        }

        private void updateAction(HttpServletRequest req, HttpServletResponse resp)
                throws SQLException, IOException, ServletException {
                // TODO Follow Jean's example regarding JSON and Gson.

                resp.setContentType("application/json");
                resp.setCharacterEncoding("UTF-8");
                resp.getWriter().write(json);
        }




}
