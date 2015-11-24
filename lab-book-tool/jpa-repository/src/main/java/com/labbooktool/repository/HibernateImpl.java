package com.labbooktool.repository;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;

//import javassist.expr.Instanceof;

@Named
@Singleton
public class HibernateImpl extends BaseRepository {

	private List<Item> itemList;

	public List<Item> select(String tableName) {

		try {

			// This step will read hibernate.cfg.xml and prepare hibernate for
			// use

			// This step will read hibernate.cfg.xml and prepare hibernate for
			// use
			if (tableName == LabConstants.DEVICES_TABLE) {
				itemList = SelectDevice.select(getSession());
			} else if (tableName == LabConstants.LAPTOPS_TABLE) {
				itemList = SelectLaptops.select(getSession());
			}
			// session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return itemList;
	}

	public void release(int id, String tableName) {
		// execute("UPDATE "+tableName+" SET status='available' WHERE id="+id);

		// This step will read hibernate.cfg.xml and prepare hibernate for
		// use
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		setSession(sessionFactory.openSession());
		Transaction tran = getSession().beginTransaction();

		String hql = "UPDATE " + tableName + "  set status = 'available' " + "WHERE id = " + id;
		Query query = getSession().createQuery(hql);
		/*
		 * query.setParameter("command", "available"); query.setParameter("id",
		 * id);
		 */
		int result = query.executeUpdate();
		System.out.println("Rows affected: " + result);
		tran.commit();
		getSession().close();

	}

	public void add(Object obj) {
		// Configuration configuration = new Configuration();
		// configuration.configure();
		// SessionFactory sessionFactory = configuration.buildSessionFactory();
		// session = sessionFactory.openSession();
		Transaction tran = getSession().beginTransaction();
		if (obj instanceof List) {
			for (Object obj1 : (List) obj) {
				addSingleObject(obj1);
			}
		} else {
			addSingleObject(obj);
		}
		tran.commit();
		getSession().close();
	}

	private void addSingleObject(Object obj) {
		if (obj instanceof Device || obj instanceof Laptop) {
			getSession().save(obj);
		}
	}
	private void deleteSingleObject(Object obj) {
		if (obj instanceof Device || obj instanceof Laptop) {
			getSession().delete(obj);
		}
	}

	public void delete(Object obj) {
		Transaction tran = getSession().beginTransaction();
		if (obj instanceof List) {
			for (Object obj1 : (List) obj) {
				deleteSingleObject(obj1);
			}
		} else {
			deleteSingleObject(obj);
		}
		tran.commit();
		getSession().close();
		
	}

}
