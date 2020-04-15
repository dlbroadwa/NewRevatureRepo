package com.company.DAO.data;

import com.company.DAO.models.Item;

import java.util.List;

public class ItemRepository implements Repository<Item, Integer> {
    @Override
    public Item findByID(Integer integer) {
        return null;
    }

    @Override
    public List<Item> findAll() {

        return null;
    }

    @Override
    public Integer save(Item obj) {
        return null;
    }

    @Override
    public void deleteByID(Integer integer) {

    }

    @Override
    public void updateByID(Integer integer) {

    }

    @Override
    public void addThing(Item obj, Integer integer) {

    }
}
