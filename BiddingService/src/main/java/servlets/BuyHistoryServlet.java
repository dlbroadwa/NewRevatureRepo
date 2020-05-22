package servlets;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.AuctionWinner;
import services.BiddingService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class BuyHistoryServlet extends HttpServlet {

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
            //Get user from jwt
            int tempUserID = Integer.parseInt(req.getParameter("bidderid"));
            PrintWriter out = resp.getWriter();
            List<AuctionWinner> auctionWinners = biddingService.getBuyHistory(tempUserID);
            //Get auction items from auctions service
            ObjectMapper om = new ObjectMapper();
            String json = om.writeValueAsString(auctionWinners);
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

    }
}