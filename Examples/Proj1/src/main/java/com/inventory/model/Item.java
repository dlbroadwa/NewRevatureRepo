package com.inventory.model;

public class Item implements SQL {
    public Item(int id, String name, double value, int shelfLife) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.shelfLife = shelfLife;
    }

    private final int id;
    private final String name;
    private final double value;
    private final int shelfLife;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getShelfLife() {
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

    @Override
    public String getSQLColumnFormat() {
        return "(\"id\", \"name\", \"value\", \"shelfLife\")";
    }

    @Override
    public String toSQLString() {
        return "(" + id + ", '" + name + "', " + value + ", " + shelfLife + ") ";
    }
}
