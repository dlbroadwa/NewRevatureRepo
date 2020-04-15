package data.dao;

import book.Item;
import data.Catalog;

import java.util.ArrayList;

public interface DAO {
    public void recordData(Catalog catalog);
    ArrayList<Item> getContent();
}
