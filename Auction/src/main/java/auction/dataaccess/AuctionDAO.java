package auction.dataaccess;

import auction.models.Auction;

import java.util.List;

public class AuctionDAO implements DAO<Auction, Integer> {
    @Override
    public boolean save(Auction obj) {
        return false;
    }

    @Override
    public List<Auction> retrieveAll() {
        return null;
    }

    @Override
    public Auction retrieveByID(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Auction obj) {
        return false;
    }

    @Override
    public boolean update(Auction newObj) {
        return false;
    }
}
