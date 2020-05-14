package com.ex.dao;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.GeoCashe;
import com.ex.model.Item;
import org.hibernate.Session;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class GpsDAOImpl_PGR implements GpsDAO {
    private HibernateUtil hibernateUtil;

    public GpsDAOImpl_PGR(){hibernateUtil = new HibernateUtil();}

    @Override
    public void addCashe(GeoCashe geoCashe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            session.persist(geoCashe);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
    }
//  place an item in a geocache. use geocacheID to find the correct cache. put item into cache
//  by setting item id to this item's id. give geocache id to geocacheHistorys, along with email.
    @Override
    public void placeItem(Item item, String email, int geoCacheID) {

    }

    @Override
    public void retrieveItem(Item item, String email, int geoCacheID) {

    }

    @Override
    public void addItem(Item item) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try{
            session.persist(item);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
    }

    @Override
    public List<GeoCashe> getAllCashes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<GeoCashe> all = new ArrayList<>();
        try{
            String hql = "From GeoCashe";
            Query query = session.createQuery(hql);
            all = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }

        return(all);
    }
}
