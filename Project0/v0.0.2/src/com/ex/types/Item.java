package com.ex.types;

import com.ex.console.ConsoleInput;

public class Item {
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
}
