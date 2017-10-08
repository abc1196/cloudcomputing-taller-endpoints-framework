package com.cloudcomputing.dimayor.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.cloudcomputing.dimayor.model.Team;

public class PersistenceManager {

    private static SessionFactory sessionFactory = null;
    private static EntityManagerFactory entityManagerFactory = null;
    
    public static Session openSession() {
		if (sessionFactory == null) {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass( Team.class );			
			
			sessionFactory = configuration.buildSessionFactory( new StandardServiceRegistryBuilder().build() );
		}
		return sessionFactory.openSession();
	}
    
    public static EntityManager openSessionEM() {
    	
    	if(entityManagerFactory == null) {
    		Configuration configuration = new Configuration();
			configuration.addAnnotatedClass( Team.class );
			
			entityManagerFactory = Persistence.createEntityManagerFactory("dimayor-pu");
    	}
    	return entityManagerFactory.createEntityManager();
	}
}