package com.ex.services;

import com.ex.dao.GpsDAO;
import com.ex.dao.GpsDAOImpl_PGR;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.Item;

import java.util.ArrayList;
import java.util.List;

public class GpsService {
    private GpsDAO gpsDAO;

    public GpsService(){this.gpsDAO = new GpsDAOImpl_PGR();}

    public GpsService(GpsDAO dao){this.gpsDAO = dao;}

    public GeoCashe createNewGeoCashe(GeoCashe cashe){
//        GeoCashe cashe = new GeoCashe();
//        cashe.setDifficultyLevel(level);
//        cashe.setGPSLocation(gpsLocation);
//        cashe.setImageurl(imageurl);
        try{
            gpsDAO.addCashe(cashe);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return cashe;
        }
    }

    public boolean createNewItem(Item item){

//        Item item = new Item();
//        item.setDescription(description);
//        item.setName(itemName);
//        item.setImage(imageurl);

        try{
            gpsDAO.addItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean placeItem(Item item, String email, int cacheID){
        try{
            gpsDAO.placeItem(item,email, cacheID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean retrieveItem(Item item, String email,int cacheID){
        try{
            gpsDAO.retrieveItem(item,email, cacheID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<GeoCashe> getAllCashes(){
        List<GeoCashe> cashes = new ArrayList<>();
        try{
            cashes = gpsDAO.getAllCashes();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return cashes;
        }
    }

}
