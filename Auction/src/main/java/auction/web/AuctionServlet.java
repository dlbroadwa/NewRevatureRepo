package auction.web;

import auction.services.AuctionService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/api/auction/*")
public class AuctionServlet extends HttpServlet {
    private AuctionService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ServletContext context = config.getServletContext();
        service = (AuctionService)context.getAttribute("auctionService");
    }

    private void tmpRemoveMe(HttpServletRequest req, HttpServletResponse resp, String method) throws ServletException, IOException {
        String url = req.getPathInfo();
        String query = req.getQueryString();
        if (query != null)
            url = url + "?" + query;
        String body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        resp.getWriter().printf("Thanks for the %s request! URL: %s   Body: %s%n", method, url, body);
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
}
