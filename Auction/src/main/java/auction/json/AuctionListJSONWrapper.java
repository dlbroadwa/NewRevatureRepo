package auction.json;

import java.util.ArrayList;
import java.util.List;

public class AuctionListJSONWrapper {
    public final List<AuctionJSONWrapper> auctions;

    public AuctionListJSONWrapper() {
        auctions = new ArrayList<>();
    }
    public AuctionListJSONWrapper(List<AuctionJSONWrapper> auctions) {
        this.auctions = auctions;
    }
}
