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
        String action = req.getServletPath();
        System.out.println(action);

        try {
            switch (action) {
                case "/create":
                    doPost(req, resp);
                    break;
                case "/read":
                    readAction(req, resp);
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
        String action = req.getServletPath();

        try {
            switch (action) {
                case "/create":
                    createAction(req, resp);
                    break;
                case "/read":
                    doGet(req, resp);
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
        empDAO.addNewEmp(newEmp);
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
}
