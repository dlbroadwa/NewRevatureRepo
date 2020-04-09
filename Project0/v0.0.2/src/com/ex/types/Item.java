package com.ex.types;

import com.ex.console.ConsoleInput;

import java.io.Serializable;

public class Item implements Serializable {
    final String NAME;
    final int ID;

    public Item(){
        NAME = ConsoleInput.getInstance().getName();
        ID = ConsoleInput.getInstance().getID();
    }

    public Item(String NAME, int ID) {
        this.NAME = NAME;
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "Item{" +
                "NAME='" + NAME + '\'' +
                ", ID=" + ID +
                '}';
    }
}
