package com.ex.dao;

import com.ex.model.GeoCashe;
import com.ex.model.Item;

import javax.mail.Session;
import java.util.ArrayList;
import java.util.List;

public class GpsDAOImpl_PGR implements GpsDAO {
    private
    Session session = HibernateUtil.getSessionFactory().
    @Override
    public void addCashe(GeoCashe geoCashe, String email) {


    }

    @Override
    public void placeItem(Item item, String email) {

    }

    @Override
    public void retrieveItem(Item item, String email) {

    }

    @Override
    public void addItem(Item item) {

    }

    @Override
    public List<GeoCashe> getAllCashes() {
        List<GeoCashe> all = new ArrayList<>();
        String hql = ""

        return all;
    }
}
