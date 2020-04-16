package com.ex.model;

import java.io.Serializable;

public class Item implements Serializable {
    public Item(int id, String name, double value, short shelfLife) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.shelf_life = shelfLife;
    }

    final int id;
    final String name;
    final double value;
    final short shelf_life;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", shelfLife=" + shelf_life +
                '}';
    }
}
