package services;
import dataaccess.PostGresConnectionUtil;
import dataaccessobjects.AuctionBidDAO;
import dataaccessobjects.AuctionWinnerDAO;
import models.AuctionBid;
import models.AuctionWinner;
import java.sql.Timestamp;
import java.util.List;

public class BiddingService {

    private final AuctionBidDAO auctionBidDAO;

    private final AuctionWinnerDAO auctionWinnerDAO;

    public BiddingService()
    {
        this.auctionBidDAO = new AuctionBidDAO(new PostGresConnectionUtil());
        this.auctionWinnerDAO = new AuctionWinnerDAO(new PostGresConnectionUtil());
    }

    public BiddingService(AuctionBidDAO auctionBidDAO, AuctionWinnerDAO auctionWinnerDAO)
    {
        this.auctionBidDAO = auctionBidDAO;
        this.auctionWinnerDAO = auctionWinnerDAO;
    }


    public boolean bid(AuctionBid auctionBid){
        boolean wasValid;
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        auctionBid.setTimestamp(timestamp);
        if(auctionBid.getBidderID() == 0)
        {
            wasValid = false;
        }
        else
        {
            AuctionBid highestBid = auctionBidDAO.getHighestBid(auctionBid.getAuctionID());
            if(highestBid.getBidAmount() < auctionBid.getBidAmount())
            {
                if(auctionBidDAO.doesBidExist(auctionBid.getAuctionID(), auctionBid.getBidderID()))
                {
                    wasValid = auctionBidDAO.update(auctionBid);
                }
                else
                {
                    wasValid = auctionBidDAO.save(auctionBid);
                }
            }
            else
            {
                wasValid = false;
            }
        }
        return wasValid;

    }

    public List<AuctionWinner> getBuyHistory(int auctionid)
    {
        return auctionWinnerDAO.retrieveAllByBidderID(auctionid);
    }

    public List<AuctionBid> getBiddingList(int bidderID)
    {
        return auctionBidDAO.retrieveAllByBidderID(bidderID);
    }

    public boolean isOutBid(int bidderID, int auctionID)
    {
        AuctionBid highestBid = auctionBidDAO.getHighestBid(auctionID);
        AuctionBid userBid = auctionBidDAO.retrieveByAuctionIDAndBidderID(bidderID,auctionID);
        return highestBid.getBidAmount() > userBid.getBidAmount();
    }

    public boolean calculateAuctionWinner(int auctionID)
    {
        AuctionBid auctionBid = auctionBidDAO.getHighestBid(auctionID);
        AuctionWinner auctionWinner = new AuctionWinner(0, auctionBid.getAuctionID(), auctionBid.getBidderID(), auctionBid.getBidAmount());
        return (auctionWinnerDAO.save(auctionWinner));
    }

}
