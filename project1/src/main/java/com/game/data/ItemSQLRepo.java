package com.game.data;

import com.game.models.Item;

import java.util.List;

public class ItemSQLRepo implements Repository<Item, Integer> {
    @Override
    public Item findById(Integer i) {
        return null;
    }

    @Override
    public List<Item> findAll() {
        return null;
    }

    @Override
    public void save(Item obj) {

    }

    @Override
    public void update(Item obj, Integer id) {

    }

    @Override
    public void delete(Integer i) {

    }
}
