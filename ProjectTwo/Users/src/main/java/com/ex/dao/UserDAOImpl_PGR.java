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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

//        System.out.println("PHONECARRIERID: " + user.getPhonecarrierid().getPhoneCarrierID());
        final int carrierId = user.getPhonecarrierid().getPhoneCarrierID();
        PhoneCarrier carrier = session.get(PhoneCarrier.class, carrierId);

        User tempUser = user;
        tempUser.setPhonecarrierid(carrier);     //This was needed to make PhoneCarrier object transient - DAN WALLACE
        Address tempAddress = user.getAddressid();
        session.saveOrUpdate(carrier);
        session.persist(tempAddress);
        session.save(tempUser);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
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
        session.getTransaction().commit();
        HibernateUtil.shutdown();
        if(results.size() > 1) {
            System.out.println("UserDAOImpl::displayUser() - ERROR - USERS RETURNED IS MORE THAN EXPECTED");
            return null;
        } else if(results.size() <= 0) {
            System.out.println("UserDAOImpl::displayUser() - ERROR - USERS RETURNED IS NULL/EMPTY");
            return null;
        } else {
            User thisUser = results.get(0);
            System.out.println(thisUser.toString());
            return thisUser;
        }
    }

    @Override
    public void updateUser(User user) throws Exception {


    }

    @Override
    public boolean disableUser(User user, boolean bIsDisabled) throws Exception {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        user.setInactiveUser(true);

        String hql = "UPDATE User set inactive_user = :inactive_user "  +
                "WHERE email = :email";
        Query query = session.createQuery(hql);
        query.setParameter("inactive_user", user.isInactiveUser());
        query.setParameter("email", user.getEmail());
        int result = query.executeUpdate();
//        System.out.println("Rows affected: " + result);


        session.getTransaction().commit();
        HibernateUtil.shutdown();


        return true;

    }
}
