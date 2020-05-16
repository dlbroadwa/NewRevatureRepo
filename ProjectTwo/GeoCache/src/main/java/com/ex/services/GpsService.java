package com.ex.services;

import com.ex.dao.GpsDAO;
import com.ex.dao.GpsDAOImpl_PGR;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
import com.ex.model.Item;

import java.util.ArrayList;
import java.util.List;

public class GpsService {
    private GpsDAO gpsDAO;

    public GpsService(){this.gpsDAO = new GpsDAOImpl_PGR();}

    public GpsService(GpsDAO dao){this.gpsDAO = dao;}

    public boolean createNewGeoCashe(GeoCashe cashe){
        try{
            gpsDAO.addCashe(cashe);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean createNewItem(Item item){
        try{
            gpsDAO.addItem(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean placeItem(Item item, GeoCasheHistorys geoCasheHistorys){
        try{
            gpsDAO.placeItem(item, geoCasheHistorys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean retrieveItem(GeoCasheHistorys geoCasheHistorys){
        try{
            gpsDAO.retrieveItem(geoCasheHistorys);
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
        }
        return cashes;
    }

    public GeoCashe findCasheByID(int id){
        GeoCashe geoCashe= new GeoCashe();
        try{
            geoCashe = gpsDAO.findCasheByID(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geoCashe;
    }

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
