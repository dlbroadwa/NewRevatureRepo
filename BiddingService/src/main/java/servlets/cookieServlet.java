package servlets;

import services.BiddingService;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class cookieServlet extends HttpServlet {

    BiddingService biddingService;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing MyServlet");
        biddingService = new BiddingService();
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



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            Cookie[] cookies = req.getCookies();
            if (cookies != null)
            {
                for(int i=0; i<cookies.length; i++)
                {
                    Cookie cookie = cookies[i];
                    System.out.println(cookie.getName() + " " + cookie.getValue());
                    PrintWriter out = resp.getWriter();
                    out.write(cookie.getName() + " " + cookie.getValue());
                }
            }
            else
            {
                //do something else for firsttime visitors
            }
        }catch(Exception e)
        {
            resp.setStatus(206);
            e.printStackTrace();
            PrintWriter out = resp.getWriter();
            out.write("Something went wrong");
        }
    }

    //Adding Bid to table
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        Cookie nameCookie = new Cookie("userName", userName);
        Cookie sessionCookie = new Cookie("sessionId", password);
        resp.addCookie(nameCookie);
        resp.addCookie(sessionCookie);
        resp.setStatus(201);
    }
}
