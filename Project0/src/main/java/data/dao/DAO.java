package data.dao;

import book.Item;
import data.Catalog;

import java.util.ArrayList;

/**
 *  Project 0:<br>
 * <br>
 *  The DAO interface serves as an interface for all means to obtain Item information from a persistent data file or
 *    database to allow for the catalog to persist between sessions in terms of changes.
 *
 *  <br> <br>
 *  Created: <br>
 *     15 April 2020, Barthelemy Martinon<br>
 *     With assistance from: August Duet<br>
 *  Modifications: <br>
 *     15 April 2020, Barthelemy Martinon,    Created class.
 *     										  Added signatures for recordData and getContent methods.
 *     										    (formerly getCatalogContent)
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 15 April 2020
 */
public interface DAO {
    ArrayList<Item> getContent();
    public void updateCheck(Item obj, int i);
    public void recordData(Catalog catalog);
}
