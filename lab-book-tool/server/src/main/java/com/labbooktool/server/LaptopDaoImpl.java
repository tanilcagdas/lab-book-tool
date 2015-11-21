package com.labbooktool.server;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Named;
import javax.inject.Singleton;

import org.hibernate.Criteria;
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

	@Override
	public List<Item> findAll() {
		try {
			Criteria criteria = getSession().createCriteria(Laptop.class);
			return criteria.list();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
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

	@Override
	public void release(int id) {

		Criteria criteria = getSession().createCriteria(Laptop.class);
		criteria.add(Restrictions.eq("id", id));
		Laptop laptop = (Laptop) criteria.uniqueResult();
		laptop.setStatus("release");
		getSession().save(laptop);
	}

	@Override
	public void reserve(int id) {

		Criteria criteria = getSession().createCriteria(Laptop.class);
		criteria.add(Restrictions.eq("id", id));
		Laptop laptop = (Laptop) criteria.uniqueResult();
		laptop.setStatus("reserve");
		getSession().save(laptop);
	}

}
