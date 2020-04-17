package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemService {
    private Repository<Item, Integer> repo;

    public ItemService(Repository<Item,Integer> repo) {this.repo=repo;}

    public void getAllItemsForCustomer() throws SQLException {
        List<Item> tmp = this.repo.findAll();
            System.out.println("Item Name, ID Number, Number in Stock");
            for (Item i : tmp){
                System.out.println(i.getItemName()+", "+i.getId()+", "+i.getOnHand());
            }

        }

    public void getAllItems() throws SQLException {
        List<Item> tmp = this.repo.findAll();
        System.out.println("Item Name, ID Number, Number in Stock, Low Level, Optimal Level");
        for (Item i : tmp){
            System.out.println(i.getItemName()+", "+i.getId()+", "+i.getOnHand()+", "+i.getLowLevel()+", "+i.getOptLevel());
        }

    }

    public Item itemByID (Integer id){
        return this.repo.findByID(id);
    }

    public void addItem(String name, int id, int onHand, int lowLevel, int optLevel){
        Item newItem = new Item();
        newItem.setItemName(name);
        newItem.setId(id);
        newItem.setOnHand(onHand);
        newItem.setLowLevel(lowLevel);
        newItem.setOptLevel(optLevel);
        this.repo.save(newItem);
    }

    public void removeItem(Integer id){
        this.repo.deleteByID(id);
    }

    public void updateItem(Item item){
        this.repo.updateByID(item);
    }

}
