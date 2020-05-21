package auction.json;

import auction.models.Auction;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class AuctionJSONWrapper {
    public int id;
    public int seller_id;
    public long end_date;
    public String starting_price;
    public String reserve_price;
    public class Item {
        public int id;
        public String name;
        public String desc;
    }
    Item item;

    public AuctionJSONWrapper(Auction auction, auction.models.Item it) {
        id = auction.getAuctionID();
        seller_id = auction.getSellerID();
        end_date = auction.getEndDate().atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
        starting_price = auction.getStartingPrice().toString();
        reserve_price = auction.getReservePrice().toString();
        item = new Item();
        item.id = it.getItemID();
        item.name = it.getName();
        item.desc = it.getDescription();
    }

    public Auction toAuction() {
        LocalDateTime endDate = LocalDateTime.ofInstant(Instant.ofEpochSecond(end_date), ZoneId.systemDefault());
        return new Auction(id, item.id, seller_id, endDate, new BigDecimal(starting_price), new BigDecimal(reserve_price));
    }

    public auction.models.Item getItem() {
        return new auction.models.Item(item.id, item.name, item.desc);
    }
}
