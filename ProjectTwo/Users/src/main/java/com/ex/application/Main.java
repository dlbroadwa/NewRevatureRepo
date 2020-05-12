package com.ex.application;

import com.ex.databaseUtils.HibernateUtil;
import com.ex.model.Address;
import com.ex.model.PhoneCarrier;
import com.ex.model.User;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        final int carrierId = 1;
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Address address = new Address(1234, "Memory Lane", "null", "Grand Rapids", "MI",
                "USA", 49341);
        PhoneCarrier carrier = session.get(PhoneCarrier.class, carrierId);
        User user = new User("Dan", "Wallace", "11234567890", carrier,
                "blah@email.com", "1234pass", address, null, 0);

        session.saveOrUpdate(carrier);
        session.persist(address);
        session.save(user);

        session.getTransaction().commit();
        HibernateUtil.shutdown();
    }
}
