package auction.services;

import auction.dataaccess.DAO;
import auction.models.Auction;
import auction.models.Item;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionService {
    private final DAO<Auction, Integer> auctionDao;
    private final DAO<Item, Integer> itemDao;

    public AuctionService(DAO<Auction, Integer> auctionDao, DAO<Item, Integer> itemDao) {
        this.auctionDao = auctionDao;
        this.itemDao = itemDao;
    }

    private boolean checkAuction(Auction auction) {
        // Check that end date is valid (after today)
        if (auction.getEndDate().isBefore(LocalDateTime.now()))
            return false;
        // Round the prices so that they have at most 2 digits after the decimal point
        auction.setStartingPrice(auction.getStartingPrice().setScale(2, RoundingMode.DOWN));
        auction.setReservePrice(auction.getReservePrice().setScale(2, RoundingMode.DOWN));

        return true;
    }

    // Wrapper for CRUD operations
    public boolean createAuction(Auction auction) {
        if (auction == null || !checkAuction(auction) || itemDao.retrieveByID(auction.getItemID()) == null)
            return false;
        return auctionDao.save(auction);
    }

    public Auction createAuction(Item item, int sellerID, LocalDateTime endDate, BigDecimal startPrice, BigDecimal reservePrice) {
        Auction newAuction = new Auction(-1, sellerID, endDate, startPrice, reservePrice);
        if (!checkAuction(newAuction))
            return null;

        // Insert item into the database (which assigns it an item ID)
        boolean result = itemDao.save(item);
        if (!result)
            return null;
        newAuction.setItemID(item.getItemID());

        result = auctionDao.save(newAuction);
        if (result)
            return newAuction;
        else
            return null;
    }

    public boolean updateAuction(Auction newAuction) {
        if (newAuction == null)
            return false;
        return auctionDao.update(newAuction);
    }

    public boolean updateAuction(Auction newAuction, Item newItem) {
        if (newAuction == null || !itemDao.save(newItem))
            return false;

        newAuction.setItemID(newItem.getItemID());
        return auctionDao.update(newAuction);
    }

    public Auction getAuction(int auctionID) {
        return auctionDao.retrieveByID(auctionID);
    }
    public Item getAuctionItem(int auctionID) {
        Auction auction = getAuction(auctionID);
        if (auction == null)
            return null;
        else
            return itemDao.retrieveByID(auction.getItemID());
    }

    public List<Auction> getAllAuctions() {
        return auctionDao.retrieveAll();
    }

    public boolean removeAuction(int auctionID) {
        return auctionDao.delete(getAuction(auctionID));
    }

    // Some other helpers
    public boolean isAuctionEnded(int auctionID) {
        Auction auction = getAuction(auctionID);
        if (auction == null)
            return false;

        return LocalDateTime.now().isAfter(auction.getEndDate());
    }

    public List<Auction> findByItemName(String query) {
        List<Auction> allAuctions = getAllAuctions();
        final String queryUpper = query.toUpperCase();

        // Yay for functional programming!
        return allAuctions.stream()
                .filter(a -> {
                    Item i = itemDao.retrieveByID(a.getItemID());
                    if (i != null)
                        return i.getName().toUpperCase().contains(queryUpper);
                    else
                        return false;
                })
                .collect(Collectors.toList());
    }
}
