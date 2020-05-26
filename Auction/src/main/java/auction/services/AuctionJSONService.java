package auction.services;

import auction.json.AuctionJSONWrapper;
import auction.json.AuctionListJSONWrapper;
import auction.models.Auction;
import auction.models.Item;

import java.util.ArrayList;
import java.util.List;

public class AuctionJSONService {
    private final AuctionService service;

    public AuctionJSONService(AuctionService service) {
        this.service = service;
    }

    public AuctionJSONWrapper getAuctionJSONObject(Auction auction) {
        if (auction == null)
            return null;
        Item item = service.getAuctionItem(auction);
        if (item == null)
            return null;
        return new AuctionJSONWrapper(auction, item);
    }
    public AuctionJSONWrapper getAuctionJSONObject(Auction auction, int hideReserveExceptForID) {
        AuctionJSONWrapper wrapper = getAuctionJSONObject(auction);
        if (wrapper != null && hideReserveExceptForID != -1 && wrapper.seller_id != hideReserveExceptForID)
            wrapper.reserve_price = "0.00";
        return wrapper;
    }

    public AuctionListJSONWrapper getAuctionJSONObjects(List<Auction> auctions) {
        if (auctions == null)
            return null;
        List<AuctionJSONWrapper> ret = new ArrayList<>();
        for (Auction auction: auctions) {
            Item item = service.getAuctionItem(auction);
            ret.add(new AuctionJSONWrapper(auction, item));
        }

        return new AuctionListJSONWrapper(ret);
    }
    public AuctionListJSONWrapper getAuctionJSONObjects(List<Auction> auctions, int hideReserveExceptForID) {
        AuctionListJSONWrapper wrapper = getAuctionJSONObjects(auctions);
        if (wrapper != null && hideReserveExceptForID != -1) {
            for (AuctionJSONWrapper w: wrapper.auctions) {
                if (w.seller_id != hideReserveExceptForID)
                    w.reserve_price = "0.00";
            }
        }
        return wrapper;
    }
}
