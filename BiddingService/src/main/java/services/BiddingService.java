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

    /**
     * Function that places a bid for an item in the auction bid table
     * @param auctionBid passed auction bid
     * @return if the bid was successfully placed
     */
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

    /**
     * Retrieves the bidder's history of wins
     * @param bidderid passed bidder id
     * @return list of wins from bidderid
     */
    public List<AuctionWinner> getBuyHistory(int bidderid)
    {
        return auctionWinnerDAO.retrieveAllByBidderID(bidderid);
    }

    /**
     * Retrieves user list of items bid on
     * @param bidderID passed bidderid
     * @return list of items user has bid on
     */
    public List<AuctionBid> getBiddingList(int bidderID)
    {
        return auctionBidDAO.retrieveAllByBidderID(bidderID);
    }

    /**
     * Checks to see if user is currenlty out bidded on an item
     * @param bidderID passed bidderid
     * @param auctionID passed userid
     * @return if the user is outbidded or not
     */
    public boolean isOutBid(int bidderID, int auctionID)
    {
        AuctionBid highestBid = auctionBidDAO.getHighestBid(auctionID);
        AuctionBid userBid = auctionBidDAO.retrieveByAuctionIDAndBidderID(bidderID,auctionID);
        return highestBid.getBidAmount() > userBid.getBidAmount();
    }

    /**
     * Caculates auction winner an inserts them into auction winner table
     * @param auctionID passed auction id
     * @return if the insert was successful or not
     */
    public boolean calculateAuctionWinner(int auctionID)
    {
        AuctionBid auctionBid = auctionBidDAO.getHighestBid(auctionID);
        AuctionWinner auctionWinner = new AuctionWinner(0, auctionBid.getAuctionID(), auctionBid.getBidderID(), auctionBid.getBidAmount());
        return (auctionWinnerDAO.save(auctionWinner));
    }

}
