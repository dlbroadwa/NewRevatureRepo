package com.game.service.itemservices;

import com.game.models.Item;

import java.util.List;

public interface ItemService {
    List<Item> findAllItems ();
    Item findItems (int id);
    boolean addItem(Item item);
    boolean deleteItem(int id);
    boolean changeItem(Item item, int id);
}
