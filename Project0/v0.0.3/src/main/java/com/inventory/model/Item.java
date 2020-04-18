package com.inventory.model;

import java.io.Serializable;

public class Item implements Serializable, SQL {
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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public short getShelfLife() {
        return shelfLife;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", shelfLife=" + shelfLife +
                '}';
    }

    public String toSQLString() {
        return "(" + id + ", \'" + name + "\', " + value + ", " + shelfLife + ")";
    }
}
