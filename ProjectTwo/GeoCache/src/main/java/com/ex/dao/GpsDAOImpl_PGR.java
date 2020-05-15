package com.ex.dao;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.GeoCashe;
import com.ex.model.GeoCasheHistorys;
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

    //  place an item in a geocache: use geocacheID to find the correct cache. put item into cache
    //  by setting item id to this item's id. give geocache id to geocacheHistorys, along with email.
    @Override
    public void placeItem(Item item, GeoCasheHistorys casheHistorys) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //casheHistorys gives us the cache (wrongly named getItemID)
        //we set the item to using setItemID
        GeoCasheHistorys history = casheHistorys;
        GeoCashe cashe = history.getItemID();
        cashe.setItemID(item);
        history.setItemID(cashe);

        try{
            session.saveOrUpdate(cashe);
            session.persist(history);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
    }

    @Override
    public void removeItem(GeoCasheHistorys casheHistorys) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        //set the item to null, thereby making the cashe empty
        GeoCasheHistorys history = casheHistorys;
        GeoCashe cashe = history.getItemID();
        cashe.setItemID(null);
        history.setItemID(cashe);
        try{
            session.saveOrUpdate(cashe);
            session.persist(history);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
    }

//    public void deleteItem(int id){
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//
//
//        String hql = "delete from Item i where i.id = :itemID";
//        Query query = session.createQuery(hql);
//        query.setParameter("itemID",id);
//        query.executeUpdate();
//        session.getTransaction().commit();
//
//    }

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

    @Override
    public GeoCashe findCasheByID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        GeoCashe tmp = new GeoCashe();
        try{
            String hql = "From GeoCashe g where g.id= :geoCacheID";
            Query query = session.createQuery(hql);
            query.setParameter("geoCacheID",id);
            List<GeoCashe> tmpList = query.getResultList();
            for(GeoCashe cashe : tmpList){
                tmp.setImageurl(cashe.getImageurl());
                tmp.setGPSLocation(cashe.getGPSLocation());
                tmp.setDifficultyLevel(cashe.getDifficultyLevel());
                tmp.setItemID(cashe.getItemID());
                tmp.setGeoCasheID(cashe.getGeoCasheID());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
        return tmp;
    }

    @Override
    public Item findItemByID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Item tmp = new Item();
        try{
            String hql = "From Item g where g.id= :itemID";
            Query query = session.createQuery(hql);
            query.setParameter("itemID",id);
            List<Item> tmpList = query.getResultList();
            for(Item item : tmpList){
                tmp.setImage(item.getImage());
                tmp.setItemID(item.getItemID());
                tmp.setName(item.getName());
                tmp.setDescription(item.getDescription());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
//            HibernateUtil.shutdown();
        }
        return tmp;
    }

}
