package com.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBuser {
	boolean authorized;
	public boolean login(String user, String pwd) {
		try{Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("SELECT PASSWORD FROM USERS WHERE NAME = '"+user+"'");
		while(resultSet.next()){
			
			
			String Password= resultSet.getString("password");
			authorized = pwd.equals(Password);
			System.out.println(user+" "+ pwd+" " +Password+" "+authorized);	
			
			}
	

	
	resultSet.close();
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
	}
return authorized;
}
	}
