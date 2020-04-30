package com.game.service.itemservices;

import com.game.data.ItemSQLRepo;
import com.game.models.Item;

import java.util.List;

public class ItemServiceImp implements ItemService{
    ItemSQLRepo irepo;

    public ItemServiceImp(ItemSQLRepo irepo){
        this.irepo=irepo;
    }

    @Override
    public List<Item> findAllItems() {
        return irepo.findAll();
    }

    @Override
    public Item findItems(int id) {
        return null;
    }

    @Override
    public boolean addItem(Item item) {
        return false;
    }

    @Override
    public boolean deleteItem(int id) {
        return false;
    }

    @Override
    public boolean changeItem(Item item, int id) {
        return false;
    }
}
