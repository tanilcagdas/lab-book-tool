package db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.lab.DBadd;

public class CreateTables {
	
	private static DataAccess dataAccess;

	public static void create(String tableName, ResultSet resultSet, String query) throws SQLException{

		
		DBadd dbadd = new DBadd();
		try {
			if (tableName.equalsIgnoreCase("devices"))
				dbadd.execute(
						"admin",
						"create table "
								+ tableName
								+ "(id int, name varchar(16), ip varchar(15), version varchar(12), status varchar(15), product varchar(30))");
			else if (tableName.equalsIgnoreCase("laptops"))
				dbadd.execute(
						"admin",
						"create table "
								+ tableName
								+ " (id int, name varchar(16), password varchar(15), os varchar(12), status varchar(15))");
			else if (tableName.equalsIgnoreCase("users"))
				dbadd.execute(
						"admin",
						"create table "
								+ tableName
								+ " (id int, name varchar(16), password varchar(15),email varchar(30))");
			else if (tableName.equalsIgnoreCase("versions"))
				dbadd.execute(
						"admin",
						"create table "
								+ tableName
								+ " (id int, version varchar(12), product varchar(30))");
			resultSet = dataAccess.executeQuery(query);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	
	}

}
