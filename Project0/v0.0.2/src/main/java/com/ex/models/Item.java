package com.ex.models;

import java.io.Serializable;

public class Item implements Serializable {
    public Item(int id, String name, double value, short shelfLife) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.shelfLife = shelfLife;
    }

    final int id;
    final String name;
    final double value;
    final short shelfLife;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", shelfLife=" + shelfLife +
                '}';
    }
}
