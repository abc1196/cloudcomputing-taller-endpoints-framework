package com.cloudcomputing.dimayor.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.cloudcomputing.dimayor.model.Team;

public class PersistenceManager {

    private static SessionFactory sessionFactory = null;
    
    public static Session openSession() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass( Team.class );			
			
			sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );
		}
		return sessionFactory.openSession();
	}
}