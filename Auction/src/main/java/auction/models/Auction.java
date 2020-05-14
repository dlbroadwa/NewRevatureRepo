package auction.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Auction {
    private int auctionID;
    private Item item;
    private int sellerID;
    private LocalDateTime endDate;
    // Floating point values are bad for currency
    private BigDecimal startingPrice;
    private BigDecimal reservePrice;

    public Auction() {}
    public Auction(int auctionID, Item item, int seller, LocalDateTime endDate, BigDecimal startingPrice, BigDecimal reservePrice) {
        this.auctionID = auctionID;
        this.item = item;
        this.sellerID = seller;
        this.endDate = endDate;
        this.startingPrice = startingPrice;
        this.reservePrice = reservePrice;
    }

    public int getAuctionID() {
        return auctionID;
    }

    public void setAuctionID(int auctionID) {
        this.auctionID = auctionID;
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

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(BigDecimal startingPrice) {
        this.startingPrice = startingPrice;
    }

    public BigDecimal getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(BigDecimal reservePrice) {
        this.reservePrice = reservePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auction auction = (Auction) o;
        return Objects.equals(item, auction.item) &&
                sellerID == auction.sellerID &&
                Objects.equals(endDate, auction.endDate) &&
                Objects.equals(startingPrice, auction.startingPrice) &&
                Objects.equals(reservePrice, auction.reservePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, sellerID, endDate, startingPrice, reservePrice);
    }
}
