package com.labbooktool.server;

import java.util.List;

import com.labbooktool.model.Item;

public interface AdminIF {
	
	
	public void add(Object obj);
	
	public void delete(Object obj);

	public List<Item> search(String tableName);
	
	public void reserve(String user, int id,String tableName )throws ClassNotFoundException ;

	public void release(String user, int id, String tableName)
			throws ClassNotFoundException;

	public List<Item> findAll();

}
