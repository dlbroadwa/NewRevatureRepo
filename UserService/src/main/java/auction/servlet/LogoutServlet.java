package auction.servlet;


import auction.services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet that logs a user out of the system
 */
public class LogoutServlet extends  HttpServlet{
    @Override
    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        UserService userService = new UserService();
        int sessionId = Integer.parseInt(request.getSession().getAttribute("sessionID").toString());
        userService.logout(sessionId);
        request.getSession().invalidate();
        response.sendRedirect("index.jsp");
    }
}
