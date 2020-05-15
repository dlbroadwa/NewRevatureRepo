package daotest;

import dataaccessobjects.AuctionBidDAO;
import dataaccessobjects.AuctionWinnerDAO;
import models.AuctionBid;
import models.AuctionWinner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import services.BiddingService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class BiddingServiceTest {
    @Mock
    private AuctionBidDAO auctionBidDAO = null;
    @Mock
    private AuctionWinnerDAO auctionWinnerDAO = null;

    private AuctionBid auctionBid= null;
    private BiddingService biddingService = null;
    private List<AuctionWinner> auctionWinners = null;
    private List<AuctionBid> auctionBids = null;
    private AuctionWinner auctionWinner = null;

    @Before
    public void init() {
        biddingService = new BiddingService(auctionBidDAO, auctionWinnerDAO);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        auctionWinners = new ArrayList<AuctionWinner>();
        auctionBids = new ArrayList<AuctionBid>();
        auctionBid = new AuctionBid(1,17, 16, 4000,timestamp);
        auctionWinner = new AuctionWinner(1, 1, 17, 4000);
        auctionWinners.add(auctionWinner);
        auctionBids.add(auctionBid);
    }


    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();


    @Test
    public void bidSuccessBidAlreadyExist()
    {
        Mockito.when(auctionBidDAO.doesBidExist(any(Integer.class),any(Integer.class))).thenReturn(true);
        Mockito.when(auctionBidDAO.update(any(AuctionBid.class))).thenReturn(true);
        Assert.assertTrue("Not Passed", biddingService.bid(auctionBid));
    }

    @Test
    public void bidSuccessBidDoesNotExist()
    {
        Mockito.when(auctionBidDAO.doesBidExist(any(Integer.class),any(Integer.class))).thenReturn(false);
        Mockito.when(auctionBidDAO.save(any(AuctionBid.class))).thenReturn(true);
        Assert.assertTrue("Not Passed",  biddingService.bid(auctionBid));
    }

    @Test
    public void bidFailUpdateFail()
    {
        Mockito.when(auctionBidDAO.doesBidExist(any(Integer.class),any(Integer.class))).thenReturn(true);
        Mockito.when(auctionBidDAO.update(any(AuctionBid.class))).thenReturn(false);
        Assert.assertFalse("Passed", biddingService.bid(auctionBid));
    }

    @Test
    public void bidFailSaveFail()
    {
        Mockito.when(auctionBidDAO.doesBidExist(any(Integer.class),any(Integer.class))).thenReturn(false);
        Mockito.when(auctionBidDAO.save(any(AuctionBid.class))).thenReturn(false);
        Assert.assertFalse("Passed", biddingService.bid(auctionBid));
    }

    @Test
    public void getBuyHistory()
    {
        boolean wasReturned;
        Mockito.when(auctionWinnerDAO.retrieveAllByBidderID(any(Integer.class))).thenReturn(auctionWinners);
        auctionWinners = biddingService.getBuyHistory(auctionBid);
        if(auctionWinners.size() > 0)
        {
            wasReturned = true;
        }
        else
        {
            wasReturned = false;
        }
        Assert.assertTrue("False", wasReturned);
    }

    @Test
    public void getBiddingList()
    {
        boolean wasReturned;
        Mockito.when(auctionBidDAO.retrieveAllByBidderID(any(Integer.class))).thenReturn(auctionBids);
        auctionBids = biddingService.getBiddingList(auctionBid);
        if(auctionWinners.size() > 0)
        {
            wasReturned = true;
        }
        else
        {
            wasReturned = false;
        }
        Assert.assertTrue("False", wasReturned);
    }
}
