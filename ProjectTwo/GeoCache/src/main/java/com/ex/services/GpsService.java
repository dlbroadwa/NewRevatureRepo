package com.ex.services;

import com.ex.dao.GpsDAO;
import com.ex.dao.GpsDAOImpl_PGR;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;

import java.util.ArrayList;
import java.util.List;

/***
 * This class is to mangage via creating, updating, and deleting items, geocaches, and geocache histories.
 *
 * @author Jordan Severance
 */
public class GpsService {
    private GpsDAO gpsDAO;

    public GpsService(){this.gpsDAO = new GpsDAOImpl_PGR();}

    public GpsService(GpsDAO dao){this.gpsDAO = dao;}

    /***
     * This method creates a new geocache and returns a boolean indicating if it was successful or not. It does not add an item. The item id should be set to null or reference an existing item id.
     * @param cashe
     * @return
     */
    public boolean createNewGeoCashe(GeoCashe cashe){
        try{
            gpsDAO.addCashe(cashe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * This method creates a new item and returns a boolean indicating if it was successful or not.
     * @param item
     * @return
     */
    public boolean createNewItem(Item item){
        try{
            gpsDAO.addItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     *
     * This method creates a new item and updates the geocache history with the user that placed the item.
     * This method returns a boolean indicating if it was successful or not.
     * @param item
     * @param geoCasheHistorys
     * @return
     */
    public boolean placeItem(Item item, GeoCasheHistorys geoCasheHistorys){
        try{
            gpsDAO.placeItem(item, geoCasheHistorys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * This method removes an item from the geocache but does not delete the item from the items table in the database.
     * @param geoCasheHistorys
     * @return
     */
    public boolean removeItem(GeoCasheHistorys geoCasheHistorys){
        try{
            gpsDAO.removeItem(geoCasheHistorys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * This method returns all geocaches.
     * @return
     */
    public List<GeoCashe> getAllCashes(){
        List<GeoCashe> cashes = new ArrayList<>();
        try{
            cashes = gpsDAO.getAllCashes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cashes;
    }

    /***
     * This method returns a geocache that matches the id passed as a parameter.
     * @param id
     * @return
     */
    public GeoCashe findCasheByID(int id){
        GeoCashe geoCashe= new GeoCashe();
        try{
            geoCashe = gpsDAO.findCasheByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geoCashe;
    }

    /***
     * This method returns an item that matches the id passed as a parameter.
     * @param id
     * @return
     */
    public Item findItemByID(int id){
        Item item = new Item();
        try{
            item = gpsDAO.findItemByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

}
