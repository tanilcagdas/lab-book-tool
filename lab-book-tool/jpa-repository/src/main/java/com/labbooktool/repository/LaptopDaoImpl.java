package com.labbooktool.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Criteria;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
import com.labbooktool.repository.BaseRepository;
import com.labbooktool.repository.DeviceDaoImpl;

@Named
@Singleton
@Transactional
public class LaptopDaoImpl extends BaseRepository implements LaptopDaoIf {

	Logger logger = LoggerFactory.getLogger(DeviceDaoImpl.class);

	@PostConstruct
	public void init() {
		super.init();
	}

	@PreDestroy
	public void destroy() {
		super.destroy();
	}

	private List<Item> itemList;

	public List<Item> findAll() {
		try {
			Criteria criteria = getSession().createCriteria(Laptop.class);
			if (criteria.list().isEmpty()) {
				Laptop laptop = new Laptop();
				laptop.setName("test");
				laptop.setStatus("available");
				add(laptop);
			}
			
			
			return criteria.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Transactional
	public void add(Item item){
//		Transaction transaction = getSession().getTransaction();
		getSession().save(item);
		getSession().flush();
//		transaction.commit();
	}
	
	public  Item findById(int id) {
		try {
			Criteria criteria = getSession().createCriteria(Laptop.class);
			criteria.add(Restrictions.eq("id", id));
			Laptop laptop = (Laptop) criteria.uniqueResult();
			return laptop;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	public void release(int id) {

		Criteria criteria = getSession().createCriteria(Laptop.class);
		criteria.add(Restrictions.eq("id", id));
		Laptop laptop = (Laptop) criteria.uniqueResult();
		laptop.setStatus(LabConstants.AVAILABLE);
		getSession().save(laptop);
		getSession().flush();
	}

	public void reserve(int id, String username) {

		Criteria criteria = getSession().createCriteria(Laptop.class);
		criteria.add(Restrictions.eq("id", id));
		Laptop laptop = (Laptop) criteria.uniqueResult();
		laptop.setStatus(username);
		getSession().save(laptop);
		getSession().flush();
	}

}
