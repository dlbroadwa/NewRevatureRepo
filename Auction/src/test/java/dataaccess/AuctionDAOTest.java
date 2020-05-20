package dataaccess;


import auction.dataaccess.AuctionDAO;
import auction.dataaccess.PostGresConnectionUtil;
import auction.models.Auction;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;

class AuctionDAOTest {
    private AuctionDAO auctionDAO = new AuctionDAO(new PostGresConnectionUtil());

    @Test
    void testSave() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        BigDecimal bigDecimal = new BigDecimal(10.0);
        Auction auction = new Auction(1, 15, timestamp.toLocalDateTime(), bigDecimal, bigDecimal);
        boolean passed = auctionDAO.save(auction);
        Assert.assertTrue("Not Passed", passed);
    }

/*
    @Test
    void retrieveAll() {
    }

    @Test
    void retrieveByID() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }*/
}