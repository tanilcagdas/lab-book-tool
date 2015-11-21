package com.labbooktool.server;

import java.util.List;

public interface AdminIF {
	
	public List search();
	
	public void add(Object obj);
	
	public void delete(Object obj);

	List search(String tableName);
	
	public void reserve(String user, int id,String tableName )throws ClassNotFoundException ;

	void release(String user, int id, String tableName)
			throws ClassNotFoundException;

}
