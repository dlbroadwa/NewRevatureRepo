package auction.models;

import java.util.Objects;

public class Item {

    private String itemName;

    private String itemDes;

    public Item(){}

    public Item(String name, String des){

        itemName = name;

        itemDes = des;

    }

    public static String getItemName() {

        return itemName;

    }

    public void setItemId(String itemName) {

        this.itemName= itemName;

    }

    public static String getItemDes() {

        return itemDes;

    }

    public void setItemDes(String itemDes) {

        this.itemDes = itemDes;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Objects.equals(itemId, item.itemId) &&
                Objects.equals(itemDes, item.itemDes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, itemDes);
    }
}