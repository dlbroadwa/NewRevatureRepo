package auction.services;

import auction.dataaccess.DAO;
import auction.models.Auction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AuctionService {
    /*
    Place item for bid - Dan
    Remove item from bid - User
    Auction List
    */
    private final DAO<Auction, Integer> dao;

    public AuctionService(DAO<Auction, Integer> dao) {
        this.dao = dao;
    }

    // Wrapper for CRUD operations
    public boolean createAuction(Auction auction) {
        return dao.save(auction);
    }

    public boolean updateAuction(Auction newAuction) {
        return dao.update(newAuction);
    }

    public Auction getAuction(int auctionID) {
        return dao.retrieveByID(auctionID);
    }

    public List<Auction> getAllAuctions() {
        return dao.retrieveAll();
    }

    public boolean removeAuction(int auctionID) {
        return dao.delete(getAuction(auctionID));
    }

    // Some other helpers
    public boolean isAuctionEnded(int auctionID) {
        Auction auction = getAuction(auctionID);
        if (auction == null)
            return false;

        /*!!!!!Switch back!!!!!!!*/
        return true;
        //return LocalDateTime.now().isAfter(Auction.getEndDate());
    }

    public List<Auction> findByItemName(String query) {
        List<Auction> allAuctions = getAllAuctions();
        final String queryUpper = query.toUpperCase();

        // Yay for functional programming!
        return allAuctions.stream()
                .filter(a -> a.getItem().getName().toUpperCase().contains(queryUpper))
                .collect(Collectors.toList());
    }
}
