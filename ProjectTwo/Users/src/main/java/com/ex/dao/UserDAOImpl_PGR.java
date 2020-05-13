package com.ex.dao;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl_PGR implements UserDAO {


    @Override
    public User loginUser(String email, String password) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM User E WHERE E.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email",email);
        List<User> results = query.list();

        System.out.println(query.list().size());

        for(User e: results){
            System.out.println(e.getFirstname());
        }
        session.getTransaction().commit();
        HibernateUtil.shutdown();

        return null;
    }


    @Override
    public void addUser(User user) throws Exception {

    }

    @Override
    public User displayUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String hql = "FROM User u WHERE u.email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("email", user.getEmail());
        List<User> results = query.list();

        //DEBUG INFO
//        System.out.println(query.list().size());
//        for(User e: results){
//            System.out.println(e.getFirstname());
//        }

        if(results.size() > 1) {
            System.out.println();
        }

        session.getTransaction().commit();
        HibernateUtil.shutdown();
        return null;
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public boolean disableUser(User user, boolean bIsDisabled) throws Exception {
        return true;
    }
}
