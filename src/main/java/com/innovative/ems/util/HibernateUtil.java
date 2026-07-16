package com.innovative.ems.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    static {
        try {

            Configuration cfg = new Configuration();

            cfg.configure();

            sessionFactory = cfg.buildSessionFactory();

            System.out.println("SessionFactory Created Successfully");

        } catch (Exception e) {

            System.out.println("SessionFactory Creation Failed");
            e.printStackTrace();

            throw new RuntimeException(e);
        }
    }

    public static SessionFactory getSessionFactory() {

        return sessionFactory;

    }

}