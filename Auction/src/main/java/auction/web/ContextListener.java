package auction.web;

import auction.dataaccess.*;
import auction.models.Auction;
import auction.models.Item;
import auction.services.AuctionService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener {
    // Data access layer
    private ConnectionUtils connectionUtils = null;
    private DAO<Auction, Integer> auctionDao = null;
    private DAO<Item, Integer> itemDao = null;

    // Service layer
    private AuctionService auctionService = null;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        System.out.println("Servlet context initialized");

        connectionUtils = new PostGresConnectionUtil();
        auctionDao = new AuctionDAO(connectionUtils);
        itemDao = new ItemDAO(connectionUtils);

        auctionService = new AuctionService(auctionDao, itemDao);

        context.setAttribute("auctionService", auctionService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("Servlet context destroyed");
    }
}
