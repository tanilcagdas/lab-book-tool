	package com.lab;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.sql.Statement;
public class ChangePassword {
	


	
		boolean authorized;
		public boolean change(String user, String pwd,String newPwd) {
			try{Connection connection=DriverManager.getConnection("jdbc:derby:labdb;create=true");
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery("SELECT password FROM USERS WHERE NAME = '"+user+"'");
			while(resultSet.next()){
				
				
				String Password= resultSet.getString("password");
				
				System.out.println(user+" "+ pwd+" " +Password+" "+authorized);	
				
				if(pwd.equals(Password)&!(pwd.equalsIgnoreCase(newPwd))){
					authorized=true;
					try{
					statement.execute("UPDATE USERS SET password='"+newPwd+"' WHERE name='"+user+"'");
					System.out.println("pasword change to "+newPwd);
					
				}catch(Exception e){e.printStackTrace();}
				
				}else{authorized=false;System.out.println("couldnt change password");}
				resultSet.close();
				}
		

		
		
			}catch(SQLException e){
				System.out.println("An exception");
				e.printStackTrace();
		}
	return authorized;
	}
		


}
