package com.lab.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.lab.model.Item;

import db.HibernateImpl;

public class ItemAdminImpl implements AdminIF {
	HibernateImpl hibernate= new HibernateImpl();

	@Override
	public List search(String tableName) {
		// TODO Auto-generated method stub
		List<Item> itemList = hibernate.select(tableName);
		return itemList;
	}

	@Override
	public void add(Object obj) {
		// TODO Auto-generated method stub

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
	public void reserve(String user, int id, String tableName)
			throws ClassNotFoundException {
			System.out.println("i will reserve item with id: "+id+" to: "+user);
			updateStatus(user, id, tableName, "reserve");
		
	}
	
	@Override
	public void release(String user, int id, String tableName)
			throws ClassNotFoundException {
			System.out.println("i will release item with id: "+id+" to: "+user);
			updateStatus(user, id, tableName, "release");
		
	}
	
	public void updateStatus(String user, int id,String tableName, String command)throws ClassNotFoundException {
		System.out.println("i will "+command+" item with id: "+id+" to: "+user);
		String compareStatus = null;
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			try {
				ResultSet resultSet=statement.executeQuery("SELECT STATUS FROM "+tableName+" WHERE id = "+id);
				while (resultSet.next()){compareStatus =resultSet.getString("status");}
			if(user.equalsIgnoreCase(compareStatus)&command.equalsIgnoreCase("release")||user.equalsIgnoreCase("admin")&command.equalsIgnoreCase("release")){try{
				//	statement.execute("UPDATE "+tableName+" SET status='available' WHERE id="+id);
					
					hibernate.release(id, tableName);
				}catch(Exception e){e.printStackTrace();}
			}else if(!user.equalsIgnoreCase(compareStatus)&command.equalsIgnoreCase("reserve")||user.equalsIgnoreCase("admin")&command.equalsIgnoreCase("reserve"))
			{
				try{statement.execute("UPDATE "+tableName+" SET status='"+user+"' WHERE id="+id);}catch(Exception e){e.printStackTrace();}
			} }catch (Exception e) {
				e.printStackTrace();
			}
					
			}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}
	}

}
