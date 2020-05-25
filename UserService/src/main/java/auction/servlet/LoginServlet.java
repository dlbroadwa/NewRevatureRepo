package auction.servlet;

import auction.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet that logs a user into the system
 */
public class LoginServlet extends HttpServlet {
    private UserService userService;
    private final int SESSION_TIMEOUT = 400;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        int sessionId = userService.loginUser(userName, password);
        if (sessionId>=1){

            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(SESSION_TIMEOUT);
            session.setAttribute("userName", userName);
            session.setAttribute("sessionId",sessionId);
            Cookie nameCookie = new Cookie("userName",userName);
            Cookie sessionCookie = new Cookie("sessionId", Integer.toString(sessionId));
            response.addCookie(nameCookie);
            response.addCookie(sessionCookie);
            response.setStatus(201);
        }
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response){
        try{
            response.setContentType("text/html");
            PrintWriter pwriter = response.getWriter();

            //Reading cookies
            Cookie c[]=request.getCookies();
            //Displaying User name value from cookie
            pwriter.print("Name: "+c[1].getValue());
            //Displaying user password value from cookie
            pwriter.print("Session ID: "+c[2].getValue());

            pwriter.close();
        }catch(Exception exp){
            System.out.println(exp);
        }
    }


}
