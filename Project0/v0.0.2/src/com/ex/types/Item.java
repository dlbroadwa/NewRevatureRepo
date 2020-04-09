package com.ex.types;

public abstract class Item {
    final String NAME;
    final int ID_NUMBER;

    public Item(String NAME, int ID_NUMBER, int shelf_life) {
        this.NAME = NAME;
        this.ID_NUMBER = ID_NUMBER;
    }
}
