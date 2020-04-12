package com.ex.types;

import java.io.Serializable;

public class Item implements Serializable {
    public Item(String name, int id) {
        this.name = name;
        this.id = id;
    }

    final String name;
    final int id;

    @Override
    public String toString() {
        return "Item{" +
                "Name='" + name + '\'' +
                ", ID=" + id +
                '}';
    }
}
