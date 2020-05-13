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

    public String getItemName() {

        return itemName;

    }

    public void setItemName(String itemName) {

        this.itemName = itemName;

    }

    public String getItemDes() {

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
        return Objects.equals(itemName, item.itemName) &&
                Objects.equals(itemDes, item.itemDes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemDes);
    }
}
