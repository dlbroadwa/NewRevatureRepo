package auction.models;

import java.util.Objects;

public class Item {
    private String name = "";
    private String description = "";

    public Item() {}
    public Item(String name, String desc) {
        if (name != null)
            this.name = name;
        if (desc != null)
            this.description = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return name.equals(item.name) &&
                description.equals(item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
