package auction.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AuctionBid {
    private int auctionID;
    private int bidderID;
    private Item item;
    private int sellerID;
    private BigDecimal bidAmount;
    private LocalDateTime timeStamp;
    public AuctionBid() {}
    public AuctionBid(int auctionID, int bidderID, int sellerID,BigDecimal bidAmount, LocalDateTime timeStamp) {
        this.auctionID = auctionID;
        this.bidderID = bidderID;
        this.sellerID = sellerID;
        this.bidAmount= bidAmount;
        this.timeStamp = timeStamp;
    }
    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
    }

    public int getBidderID() {
        return bidderID;
    }

    public void setBidderID(int bidderID) {
        this.bidderID = bidderID;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getSellerID() {
        return sellerID;
    }

    public void setSellerID(int sellerID) {
        this.sellerID = sellerID;
    }

    public BigDecimal getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        this.bidAmount = bidAmount;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }



}
