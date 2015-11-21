package com.labbooktool.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
//import javassist.expr.Instanceof;

@Named
@Singleton
@Transactional
public class DeviceDaoImpl extends BaseRepository implements DeviceDaoIf{
	
	Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);
	

	@PostConstruct
	public void init(){
		super.init();
	}
	
	@PreDestroy
	public void destroy(){
		super.destroy();
	}
	
	
	private  List<Item> itemList;

	@Override
	public  List<Item> findAll() {
		try {
				Criteria criteria = getSession().createCriteria(Device.class);
				return criteria.list(); 
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return itemList;
	}

	@Override
	public  Item findById(int id) {
		try {
			Criteria criteria = getSession().createCriteria(Device.class);
			criteria.add(Restrictions.eq("id", id));
			Device device = (Device) criteria.uniqueResult();
			return device;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public  void release(int id) {
		
		Criteria criteria = getSession().createCriteria(Device.class);
		criteria.add(Restrictions.eq("id", id));
		Device device = (Device) criteria.uniqueResult();
		device.setStatus("release");
		getSession().save(device);
	}

	@Override
	public  void reserve(int id) {
		
		Criteria criteria = getSession().createCriteria(Device.class);
		criteria.add(Restrictions.eq("id", id));
		Device device = (Device) criteria.uniqueResult();
		device.setStatus("reserve");
		getSession().save(device);
	}

	public void add(Object obj) {
//		Configuration configuration = new Configuration();
//		configuration.configure();
//		SessionFactory sessionFactory = configuration.buildSessionFactory();
//		session = sessionFactory.openSession();	 
		Transaction tran = getSession().beginTransaction();  
		if (obj instanceof List){
			for (Object obj1 : (List)obj) {
				addSingleObject(obj1);
			}
		}else{
			addSingleObject(obj);
		}
		tran.commit(); 
		getSession().close();
	}

	private void addSingleObject(Object obj) {
		if (obj instanceof Device || obj instanceof Laptop){
			getSession().save(obj);
			}
	}

}

