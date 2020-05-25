package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.AuctionBid;
import services.BiddingService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class BiddingServlet extends HttpServlet {
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
            resp.setCharacterEncoding("UTF-8");
            int tempUserID = Integer.parseInt(req.getParameter("bidderid"));
            PrintWriter out = resp.getWriter();
            List<AuctionBid> auctionBids = biddingService.getBiddingList(tempUserID);
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(auctionBids);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            out.print(json);
            out.flush();
        }catch(Exception e)
        {
            resp.setStatus(206);
            PrintWriter out = resp.getWriter();
            out.write("Something Went Wrong");
        }

    }

    //Adding Bid to table
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try
        {
            boolean bidValid = false;
            int auctionID = Integer.parseInt(req.getParameter("auctionid"));
            double amount = Double.parseDouble(req.getParameter("amount"));
            LocalDateTime rightNow = LocalDateTime.now();
            //Get User ID from User Service
            AuctionBid auctionBid = new AuctionBid(auctionID, 12, 0, amount,  rightNow);
            bidValid = biddingService.bid(auctionBid);
            if(bidValid)
            {
                resp.setStatus(201);
                PrintWriter out = resp.getWriter();
                out.write("Bid Paseed");
            }
            else
            {
                resp.setStatus(206);
                PrintWriter out = resp.getWriter();
                out.write("Bid Failed Failed");
            }
        }catch(Exception e)
        {
            resp.setStatus(206);
            PrintWriter out = resp.getWriter();
            out.write("Something Went Wrong");
        }


    }
}
