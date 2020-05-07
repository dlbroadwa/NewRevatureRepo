package bank.servlets;

import bank.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/***
 *
 * @author Shawyn Kane
 */
public class LoginServlet extends HttpServlet {
    private UserServices us;
    private final int SESSION_MAX_AGE = 300;

    @Override
    public void init() throws ServletException {
        super.init();
        us = new UserServices();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getContentType());
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        if (us.login(email, password)) {
            HttpSession session = req.getSession();
            session.setMaxInactiveInterval(SESSION_MAX_AGE);
            session.setAttribute("userEmail", email);
            resp.setStatus(201);
        }
        else resp.setStatus(206);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
