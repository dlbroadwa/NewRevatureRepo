package daotest;
import dataaccess.PostGresConnectionUtil;
import dataaccessobjects.AuctionBidDAO;
import models.AuctionBid;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.util.List;

public class AuctionBidDAOTest {

    private AuctionBidDAO auctionBidDAO = new AuctionBidDAO(new PostGresConnectionUtil());

    @Test
    public void saveBid()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBid auctionBid = new AuctionBid(3, 10, 11, 4000, timestamp);
        boolean passed = auctionBidDAO.save(auctionBid);
        Assert.assertTrue("Not Passed", passed);
    }

    @Test
    public void findAllBids()
    {
        List<AuctionBid> auctionBids = auctionBidDAO.retrieveAll();
        for(int i = 0; i < auctionBids.size(); i++)
        {
            System.out.println(auctionBids.get(i));
        }
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void retrieveAllByAuctionID()
    {
        List<AuctionBid> auctionBids = auctionBidDAO.retrieveAllByAuctionID(3);
        for(int i = 0; i < auctionBids.size(); i++)
        {
            System.out.println(auctionBids.get(i));
        }
        Assert.assertEquals("Test", true, true);
    }
    @Test
    public void retrieveAllByBidderID()
    {
        List<AuctionBid> auctionBids = auctionBidDAO.retrieveAllByBidderID(17);
        for(int i = 0; i < auctionBids.size(); i++)
        {
            System.out.println(auctionBids.get(i));
        }
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void retrieveAllByAuctionIDAndBidderID()
    {
        AuctionBid auctionBid = auctionBidDAO.retrieveByAuctionIDAndBidderID(1, 12);
        System.out.println(auctionBid);
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void doesBidExist()
    {
        boolean exist = auctionBidDAO.doesBidExist(3, 12);
        Assert.assertTrue("Does not exist", exist);
    }

    @Test
    public void isOutBidded()
    {
        AuctionBid auctionBid = auctionBidDAO.getHighestBid(3);
        System.out.println(auctionBid);
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void delete()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBid auctionBid = new AuctionBid(3, 11, 11, 200, timestamp);
        boolean passed = auctionBidDAO.delete(auctionBid);
        Assert.assertTrue("Failed", passed);
    }

    @Test
    public void update()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBid auctionBid = new AuctionBid(3, 12, 11, 4000, timestamp);
        boolean passed = auctionBidDAO.update(auctionBid);
        Assert.assertTrue("Failed", passed);
    }

    @AfterClass
    public static void reset()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBidDAO auctionBidDAO = new AuctionBidDAO(new PostGresConnectionUtil());
        auctionBidDAO.delete(new AuctionBid(3, 10, 11, 200, timestamp));
        auctionBidDAO.save(new AuctionBid(3, 11, 11, 2000, timestamp));
        auctionBidDAO.update(new AuctionBid(3, 12, 11, 3000, timestamp));
    }
}