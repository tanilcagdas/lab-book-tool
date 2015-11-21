package com.labbooktool.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import com.labbooktool.model.Item;
import com.labbooktool.repository.HibernateImpl;
import com.labbooktool.repository.ItemRepository;

@Named
public class ItemAdminImpl implements AdminIF {

	@Inject
	HibernateImpl hibernate;

	@Inject
	ItemFactory itemFactory;

	// hibernate= new HibernateImpl();

	@SuppressWarnings("rawtypes")
	@Override
	public List search(String tableName) {
		try {
			ItemRepository repository = itemFactory.getRepository(tableName);
			return repository.findAll();
		} catch (Exception e) {
			List<Item> itemList = hibernate.select(tableName);
			return itemList;
		}
	}

	@Override
	public void add(Object obj) {
		hibernate.add(obj);

	}

	@Override
	public void delete(Object obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public List search() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reserve(String user, int id, String tableName) throws ClassNotFoundException {
		System.out.println("i will reserve item with id: " + id + " to: " + user);
		updateStatus(user, id, tableName, "reserve");

	}

	@Override
	public void release(String user, int id, String tableName) throws ClassNotFoundException {
		System.out.println("i will release item with id: " + id + " to: " + user);
		updateStatus(user, id, tableName, "release");

	}

	public void updateStatus(String user, int id, String tableName, String command) throws ClassNotFoundException {
		System.out.println("i will " + command + " item with id: " + id + " to: " + user);
		String compareStatus = null;
		ItemRepository repository = itemFactory.getRepository(tableName);
		try {
			Item item = repository.findById(id);
			if (item != null) {
				compareStatus = item.getStatus();
			}else{
				return;
			}
			if (user.equalsIgnoreCase(compareStatus) & command.equalsIgnoreCase("release") || user.equalsIgnoreCase("admin") & command.equalsIgnoreCase("release")) {
				try {
					// statement.execute("UPDATE "+tableName+" SET status='available' WHERE id="+id);
					repository.release(id);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (!user.equalsIgnoreCase(compareStatus) & command.equalsIgnoreCase("reserve") || user.equalsIgnoreCase("admin") & command.equalsIgnoreCase("reserve")) {
				try {
					repository.reserve(id);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
