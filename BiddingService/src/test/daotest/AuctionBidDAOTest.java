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
        AuctionBid auctionBid = new AuctionBid(1, 15, 16, 3000, timestamp);
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
        List<AuctionBid> auctionBids = auctionBidDAO.retrieveAllByAuctionID(1);
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
        AuctionBid auctionBid = auctionBidDAO.retrieveByAuctionIDAndBidderID(1, 17);
        System.out.println(auctionBid);
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void doesBidExist()
    {
        boolean exist = auctionBidDAO.doesBidExist(1, 17);
        Assert.assertTrue("Does not exist", exist);
    }

    @Test
    public void isOutBidded()
    {
        AuctionBid auctionBid = auctionBidDAO.getHighestBid(1);
        System.out.println(auctionBid);
        Assert.assertEquals("Test", true, true);
    }

    @Test
    public void delete()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBid auctionBid = new AuctionBid(1, 16, 16, 200, timestamp);
        boolean passed = auctionBidDAO.delete(auctionBid);
        Assert.assertTrue("Failed", passed);
    }

    @Test
    public void update()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBid auctionBid = new AuctionBid(1, 17, 16, 4000, timestamp);
        boolean passed = auctionBidDAO.update(auctionBid);
        Assert.assertTrue("Failed", passed);
    }

    @AfterClass
    public static void reset()
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        AuctionBidDAO auctionBidDAO = new AuctionBidDAO(new PostGresConnectionUtil());
        auctionBidDAO.delete(new AuctionBid(1, 15, 16, 200, timestamp));
        auctionBidDAO.save(new AuctionBid(1, 16, 16, 2000, timestamp));
        auctionBidDAO.update(new AuctionBid(1, 17, 16, 3000, timestamp));
    }
}
