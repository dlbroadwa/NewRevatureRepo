package com.ex.databaseUtils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
//            String currentDirectory = System.getProperty("user.dir");
//            Configuration configuration = new Configuration().configure(currentDirectory + "\\src\\main\\resources\\hibernate.cfg.xml");
//            Configuration configuration = new Configuration().configure("E:\\VSProjs\\REVATURE\\2004-apr06-java\\ProjectTwo\\Users\\src\\resources\\hibernate.cfg.xml");
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
}
