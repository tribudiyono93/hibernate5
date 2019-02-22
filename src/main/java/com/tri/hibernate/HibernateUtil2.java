package com.tri.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil2 {
	private static SessionFactory buildSessionFactory(String environment) {

		String fullFilename = "hibernate.cfg.xml";

		try {
			// Create the ServiceRegistry from hibernate.cfg.xml
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure(fullFilename).build();

			// Create a metadata sources using the specified service registry.
			Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder()//
					.build();

			return metadata.getSessionFactoryBuilder().build();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	/*
	 * A method to create a new session - thus we manage the session ourselves
	 * 
	 * @return a new KokattoSession instance, which is a wrapper around Hibernate
	 * Session
	 */
	public static SessionFactory createSessionFactory(String hibernateFilename) {
		SessionFactory sessionFactory = buildSessionFactory(hibernateFilename);
		return sessionFactory;
	}
}
