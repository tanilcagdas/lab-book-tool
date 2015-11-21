package com.labbooktool.repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class BaseRepository {
	
	private Session session = null;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	@PostConstruct
	public void init(){
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();
	}
	
	@PreDestroy
	public void destroy(){
		session.close();
	}

}
