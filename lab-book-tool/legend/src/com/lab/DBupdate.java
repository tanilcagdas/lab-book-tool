package com.lab;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class DBupdate {

	public void reserve(String user, String id,String tableName )throws ClassNotFoundException {
		
		System.out.println("i will reserve item with id: "+id+" to: "+user);
		updateStatus(user, id, tableName, "reserve");
				
			
			
	}
	
	public void release(String user, String id,String tableName )throws ClassNotFoundException {
		
		System.out.println("i will release item with id: "+id+" to: "+user);
		updateStatus(user, id, tableName, "release");
			
			
	}
	
	public void updateStatus(String user, String id,String tableName, String command)throws ClassNotFoundException {
		
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
					statement.execute("UPDATE "+tableName+" SET status='available' WHERE id="+id);
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
	
	public void update(String tableName,String id,String name,String ip,String version)throws ClassNotFoundException{
		System.out.println("DBupdate.update tableName&id&name&ip&version"+tableName+id+name+ip+version);
	try{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
		Statement statement=connection.createStatement();
		String variable=null;
				try{
				statement.execute("UPDATE "+tableName+" SET name='"+name+"' WHERE id="+id);
			}catch(Exception e){e.printStackTrace();}
			
			if(tableName.equalsIgnoreCase("Devices")){variable="ip";}
			else if(tableName.equalsIgnoreCase("Laptops")){variable="password";}
				try{
				statement.execute("UPDATE "+tableName+" SET "+variable+"='"+ip+"' WHERE id="+id);
			}catch(Exception e){e.printStackTrace();}
			
			if(tableName.equalsIgnoreCase("Devices")){variable="version";}
			else if(tableName.equalsIgnoreCase("Laptops")){variable="os";}
			
			try{
				statement.execute("UPDATE "+tableName+" SET "+variable+"='"+version+"' WHERE id="+id);
			}catch(Exception e){e.printStackTrace();}
			
			
		
		
		
				
		}catch(SQLException e){
			System.out.println("An exception");
			e.printStackTrace();
		}
	}//end o updateparameter

	public void updateEmail(String user, String email) {
		System.out.println("DBupdate.update "+user+" "+email);
		String tableName="users";
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			
					try{statement.execute("UPDATE "+tableName+" SET email='"+email+"' WHERE name='"+user+"'");
				}catch(Exception e){e.printStackTrace();}
				
		
	}catch(Exception e){e.printStackTrace();}
	
	}

	public void resetPassword(String user) {
		System.out.println("DBupdate.resetPassword user is: "+user);
		String tableName="users";
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			
					try{statement.execute("UPDATE "+tableName+" SET password='"+user+"' WHERE name='"+user+"'");
				}catch(Exception e){e.printStackTrace();}
				
		
	}catch(Exception e){e.printStackTrace();}
	
	}
		
		}
	
