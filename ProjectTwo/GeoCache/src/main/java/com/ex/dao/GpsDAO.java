package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.Item;

import java.util.List;

public interface GpsDAO {
    public void addCashe(GeoCashe geoCashe);
    public void placeItem(Item item, String email, int geoCacheID);
    public void retrieveItem(Item item, String email, int geoCacheID);
    public void addItem(Item item);
    public List<GeoCashe> getAllCashes();
}
