package com.lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBadd {
	public String injectDevice(String tableName, int id,String name, String ip,String version,String status,String product) throws ClassNotFoundException {
		String list="";
		System.out.println("from dbadd table name : "+tableName+" id : "+id+" name : "+name+" ip : "+ip+" version : "+version+ " status : "+status+" product : "+ product);
		
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			try{statement.execute("create table "+tableName+" (id int, name varchar(16), ip varchar(15), version varchar(12), status varchar(15), product varchar(30))");
				}catch(Exception e){e.printStackTrace();
			System.out.println(tableName+" Table already created");
		}
			System.out.println("try to insert into table "+tableName);statement =connection.createStatement();
			statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+id+",'"+name+"','"+ip+"','"+version+"','"+status+"','"+product+"')");
				}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}System.out.println("devicesdan gelen liste "+list);return list;
	}
	
	public String injectLaptop(String tableName, int id,String name, String password,String os,String status) throws ClassNotFoundException {
		String liste="";
		System.out.println("from DBadd table name : "+tableName+" id : "+id+" name : "+name+" password : "+password+" o/s : "+os+ " status : "+status);
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			try{statement.execute("create table "+tableName+" (id int, name varchar(16), password varchar(15), os varchar(12), status varchar(15))");
				}catch(Exception e){e.printStackTrace();
			System.out.println(tableName+" Table already created");
		}
			System.out.println("try to insert into table "+tableName);statement =connection.createStatement();
			statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+id+",'"+name+"','"+password+"','"+os+"','"+status+"')");
				}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}System.out.println("laptoplardan gelen liste "+liste);return liste;
	}
	public String injectUser(String tableName, int id,String name, String password,String email) throws ClassNotFoundException {
		String list="";
		System.out.println("dbadd.inject table name : "+tableName+" id : "+id+" name : "+name+" password : "+password+" email : "+email);
		
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			try{statement.execute("create table "+tableName+" (id int, name varchar(16), password varchar(15),email varchar(30))");
				}catch(Exception e){e.printStackTrace();
			System.out.println(tableName+" Table already created");
		}
			System.out.println("try to insert into table "+tableName);statement =connection.createStatement();
			statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+id+",'"+name+"','"+password+"','"+email+"')");
				}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}System.out.println("userdan gelen liste "+list);return list;
	}
	
	public String injectVersion(String tableName, int id, String version,String product) throws ClassNotFoundException {
		String versionList="";
		System.out.println("from dbadd table name : "+tableName+"id : "+id+" version : "+version+ " product : "+ product);
		
		try{
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			try{statement.execute("create table "+tableName+" (id int, version varchar(12), product varchar(30))");
				}catch(Exception e){e.printStackTrace();
			System.out.println(tableName+" Table already created");
		}
			System.out.println("try to insert into table "+tableName);statement =connection.createStatement();
			statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+id+",'"+version+"','"+product+"')");
				}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
			}System.out.println("devicesdan gelen liste "+versionList);return versionList;
	}
	

public void execute(String tableName,String query) throws ClassNotFoundException {
	
	System.out.println("dbadd.execute table name : "+tableName+" query : "+query);
	
	try{
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
		Statement statement=connection.createStatement();
		statement.execute(query);
			}catch(Exception e){e.printStackTrace();
			
		
		
		
		
}
	}
}