package web;

import dao.EmpDAO;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EmpServlet extends HttpServlet {

    // Instance Variables
    private EmpDAO empDAO;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing MyServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy MyServlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        /*
         * init -- beginning of the servlet lifecycle
         *         it runs once, if the servlet has never been initialize
         *         when the first request to a matching url pattern is made.
         *         You can preload servlet with <load-on-startup> in the web.xml.
         * */
        System.out.println("Init MyServlet");
        empDAO = new EmpDAO();
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String action = req.getServletPath();
        String URL = req.getRequestURL().toString();
        String action = null;

        if ( URL.contains("/create") ) {
            action = "/create";
        } else if ( URL.contains("/read") ) {
            action = "/read";
        } else if ( URL.contains("/update") ) {
            action = "/update";
        } else if ( URL.contains("/delete") ) {
            action = "/delete";
        }

        System.out.println(action);

        try {
            switch (action) {
                case "/create":
                    doPost(req, resp);
                    break;
                case "/read":
                    readAction(req, resp);
                    break;
                case "/update":
                    doPost(req, resp);
                    break;
                case "/delete":
                    doPost(req, resp);
                    break;
                default:
                    System.err.println("Default reached while running GET, meaning bad path.");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //String action = req.getServletPath();
        String URL = req.getRequestURL().toString();
        String action = null;

        if ( URL.contains("/create") ) {
            action = "/create";
        } else if ( URL.contains("/read") ) {
            action = "/read";
        } else if ( URL.contains("/update") ) {
            action = "/update";
        } else if ( URL.contains("/delete") ) {
            action = "/delete";
        }

        try {
            switch (action) {
                case "/create":
                    createAction(req, resp);
                    break;
                case "/read":
                    doGet(req, resp);
                    break;
                case "/update":
                    updateAction(req, resp);
                    break;
                case "/delete":
                    deleteAction(req, resp);
                    break;
                default:
                    System.err.println("Default reached while running POST, meaning bad path.");
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    // Switch Statement Case Methods

    private void createAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String lastname = req.getParameter("lastnameC");
        String idNum = req.getParameter("idnumC");
        String username = req.getParameter("usernameC");
        String password = req.getParameter("passwordC");
        Employee newEmp = new Employee(lastname,Integer.parseInt(idNum),username,password);
        Employee result = empDAO.addNewEmp(newEmp);

        if(result != null) {
            resp.getWriter().write("Created : " + result.toPOSTString());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Employee was added, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void readAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String idNum = req.getParameter("idnumR");
        Employee result = empDAO.getEmp(Integer.parseInt(idNum));
//        System.out.println(result.toGETString());

        if(result != null) {
            resp.getWriter().write("Search Result: " + result.toGETString());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Results found, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void updateAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String lastname = req.getParameter("lastnameU");
        String idNum = req.getParameter("idnumU");
        String username = req.getParameter("usernameU");
        String password = req.getParameter("passwordU");
        Employee upToDateEmp = new Employee(lastname,Integer.parseInt(idNum),username,password);
        Employee result = empDAO.updateEmp(upToDateEmp);

        if(result != null) {
            resp.getWriter().write("Update Result: " + result.toPOSTString());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Employee was found with matching ID, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }

    private void deleteAction(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        String idNum = req.getParameter("idnumD");
        Employee result = empDAO.removeEmp(Integer.parseInt(idNum));

        if(result != null) {
            resp.getWriter().write("Deleted : " + result.toPOSTString());
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("No Employee with Matching ID found, or there is a problem.");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }
    }
}
