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
        boolean result;
        if (this == o) {
            result = true;
        } else if (!(o instanceof Item)) {
            result = false;
        } else {
            Item item = (Item) o;
            result = Objects.equals(itemName, item.itemName) &&
                    Objects.equals(itemDes, item.itemDes);
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemName, itemDes);
    }

}
