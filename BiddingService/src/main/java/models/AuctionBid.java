package models;

import java.sql.Timestamp;

public class AuctionBid {
    private int auctionID;
    private int bidderID;
    private int sellerID;
    private double bidAmount;
    private Timestamp timestamp;

    public AuctionBid(){
        auctionID = 0;
        bidderID = 0;
        sellerID = 0;
        bidAmount = 0;
        timestamp = null;
    }

    public AuctionBid(int auctionID, int bidderID, int sellerID, double bidAmount, Timestamp timestamp)
    {
        this.auctionID = auctionID;
        this.bidderID = bidderID;
        this.sellerID = sellerID;
        this.bidAmount = bidAmount;
        this.timestamp = timestamp;
    }
    public int getSellerID() {
        return sellerID;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionId) {
        this.auctionID = auctionId;
    }

    public int getBidderID() {
        return bidderID;
    }

    public void setBidderID(int bidderID) {
        this.bidderID = bidderID;
    }
}