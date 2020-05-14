package auction.servlet;

import auction.dataaccess.UserDAO;
import auction.models.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class UserServlet extends HttpServlet {
    private UserDAO userDAO;
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
        super.init();
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        ObjectMapper om = new ObjectMapper();
//        Connection connection = null;
//        if(req.getContentType().equals("application/json")) {
//            userDAO = new UserDAO();
//
//            if(hr.name.trim().length() > 0) {
//                hr.name = "Hello, " + hr.name;
//                resp.setContentType("application/json");
//                resp.setStatus(200);
//                resp.getWriter().write(om.writeValueAsString(hr));
//            } else {
//                resp.setStatus(400);
//            }
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userDAO = new UserDAO();
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String ccNumber = req.getParameter("creditCardNumber");
        String roleString = req.getParameter("role");
        int role = Integer.parseInt(roleString);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setCreditCardNumber(ccNumber);
        user.setRole(role);

        userDAO.save(user);
    }

//    private HelloRequest proceesJson(HttpServletRequest r) throws IOException {
//        ObjectMapper om = new ObjectMapper();
//        HelloRequest req = om.readValue(r.getReader(), HelloRequest.class);
//        return req;
//    }
}

