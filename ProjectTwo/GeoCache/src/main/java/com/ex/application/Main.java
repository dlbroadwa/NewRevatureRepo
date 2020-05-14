package com.ex.application;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.DifficultyLevel;
import com.ex.model.GeoCashe;
import com.ex.model.Item;
import com.ex.services.GpsService;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        HibernateUtil.shutdown();
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
        GpsService service = new GpsService();
        Item item = new Item("thimble","or a kiss to Peter Pan", "image4");
//        service.createNewItem(item);
        GeoCashe cashe = new GeoCashe(item,"get it","gpsyeeeeea", new DifficultyLevel(2));
        service.createNewGeoCashe(cashe);
        List<GeoCashe> all = service.getAllCashes();
        System.out.println(all);
//        session.flush();

//        boolean success = service.createNewItem(item);
//        try{
//            session.save(item);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            session.close();
//            HibernateUtil.shutdown();
//        }



    }
}