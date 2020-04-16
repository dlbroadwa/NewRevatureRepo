package com.company.services;

import com.company.DAO.data.Repository;
import com.company.DAO.models.Item;

import java.sql.SQLException;
import java.util.List;

public class ItemService {
    private Repository<Item, Integer> repo;

    public ItemService(Repository<Item,Integer> repo) {this.repo=repo;}

    public List<Item> getAllItems() throws SQLException {
        return this.repo.findAll();
    }
    public Item itemByID (Integer id){
        return this.repo.findByID(id);
    }
    public void addItem(Item item){
        this.repo.save(item);
    }
    public void removeItem(Integer id){
        this.repo.deleteByID(id);
    }
    public void updateItem(Integer id){
        this.repo.updateByID(id);
    }
}
