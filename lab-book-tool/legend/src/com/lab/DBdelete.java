package com.lab;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.sql.Statement;

public class DBdelete {
	
	public void delete(String tableName, String index)throws ClassNotFoundException{
		
		try{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
		Statement statement=connection.createStatement();
		
		/*will try to delete here*/
		try{
			statement.execute("DELETE FROM "+tableName+" WHERE id = "+Integer.parseInt(index));
			System.out.println("deleted "+index);
				}catch(Exception e){e.printStackTrace();
		}
		
				
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
		}
			
	

}}
