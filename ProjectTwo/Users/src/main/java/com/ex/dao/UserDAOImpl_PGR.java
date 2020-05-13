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

        final int carrierId = 1;
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();


//        Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
//                "USA", 49341);
//        PhoneCarrier carrier = session.get(PhoneCarrier.class, carrierId);

        String hql = "FROM Users E WHERE E.email = :email";
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
    public void logoutUser() {
        Connection connection = null;

        
    }

    @Override
    public void addUser(User user) throws Exception {

    }

    @Override
    public List<User> displayUser() {
        return null;
    }

    @Override
    public void updateUser(User user) throws Exception {

    }

    @Override
    public void deleteUser(User user) throws Exception {

    }
}
