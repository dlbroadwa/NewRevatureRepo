package data;

import models.Item;
import services.Catalog;

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
 *     17 April 2020, Barthelemy Martinon,    Added signature for addItem.
 * <br>
 *     21 April 2020, Barthelemy Martinon,    Added signature for getItem.
 * <br>
 *  @author Barthelemy Martinon   With assistance from: August Duet
 *  @version 21 April 2020
 */
public interface DAO {
    public ArrayList<Item> getContent();
    public Item getItem(int i);
    public void addItem(Item obj);
    public void removeItem(Item obj);
    public void updateCheck(Item obj, int i);
    public void recordData(Catalog catalog);
}
