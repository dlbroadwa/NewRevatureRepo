package auction.servlet;

import auction.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


public class UserServlet extends HttpServlet {
    private UserService service;
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
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        service = (UserService) context.getAttribute("auctionService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tmpRemoveMe(req, resp, "GET");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tmpRemoveMe(req, resp, "POST");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tmpRemoveMe(req, resp, "PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        tmpRemoveMe(req, resp, "DELETE");
    }

    private void tmpRemoveMe(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {
        String url = req.getPathInfo();
        String query = req.getQueryString();
        if (query != null)
            url = url + "?" + query;
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.getWriter().printf("Thanks for the %s request! URL: %s   Body: %s%n", method, url, body);
    }
}

