package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.Item;

import java.util.List;

public interface GpsDAO {
    public void addCashe(GeoCashe geoCashe, String email);
    public void placeItem(Item item, String email);
    public void retrieveItem(Item item, String email);
    public void addItem(Item item);
    public List<GeoCashe> getAllCashes();
}
