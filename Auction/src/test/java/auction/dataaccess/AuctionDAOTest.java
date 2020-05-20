package auction.dataaccess;

import auction.dataaccess.AuctionDAO;
import auction.dataaccess.PostGresConnectionUtil;
import auction.models.Auction;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class AuctionDAOTest extends TestCase {
    private AuctionDAO auctionDAO = new AuctionDAO(new PostGresConnectionUtil());
    protected Auction testAuction=null;
    public void testSave() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BigDecimal bigDecimal = new BigDecimal(10.0);
        Auction auction = new Auction(1, 12, timestamp.toLocalDateTime(), bigDecimal, bigDecimal);
        boolean saved = auctionDAO.save(auction);
        Assert.assertTrue("Save Failed", saved);
    }

    public void testUpdate() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BigDecimal bigDecimal = new BigDecimal(10.0);
        Auction auction = new Auction(3,1, 12, timestamp.toLocalDateTime(), bigDecimal, bigDecimal);

        boolean updated = auctionDAO.update(auction);
        Assert.assertTrue("UPDATE Failed",updated);
    }

    public void testRetrieveByID() {
        int testID=3;
        testAuction = auctionDAO.retrieveByID(testID);
        Assert.assertTrue("Assertion Returned",testID == testAuction.getAuctionID());
    }

    public void testRetrieveAll() {
        List<Auction> auctions= auctionDAO.retrieveAll();
        for(int i = 0; i < auctions.size(); i++)
        {
            System.out.println(auctions.get(i));
        }
        Assert.assertFalse("Retrieval Failed",auctions.isEmpty());
    }



    public void testDelete() {  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BigDecimal bigDecimal = new BigDecimal(10.0);
        Auction auction = new Auction(1, 12, timestamp.toLocalDateTime(),bigDecimal, bigDecimal);
        boolean deleted = auctionDAO.delete(auction);
        Assert.assertTrue("Delete Failed", deleted);
    }


}

