package com.labbooktool.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataAccess {
	
	private static Connection connection = null;
	private static final String dbURL = "jdbc:derby:labdb;create=true";

	
	



	private static final DataAccess instance = new DataAccess();

	private DataAccess() {
		initialize();
	}

	public static DataAccess getInstance() {
		return instance;
	}

	private void initialize() {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
			connection = DriverManager.getConnection(dbURL);
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found.\n");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Connection error.\n");
			e.printStackTrace();
		}
	}
	


	public ResultSet executeQuery(String query) throws SQLException {
		ResultSet resultSet = null;
		Statement statement = connection.createStatement();
		resultSet = statement.executeQuery(query);
		return resultSet;
	}

	public boolean update(String sql) throws SQLException {
		boolean executeResult = false;
		ResultSet resultSet = null;
		//TODO update
		Statement statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		
		return executeResult;
	}




}
