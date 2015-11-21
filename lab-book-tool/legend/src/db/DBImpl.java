package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lab.model.Device;
import com.lab.model.Item;
import com.lab.model.Laptop;






public class DBImpl {
	
	private static final DBImpl instance = new DBImpl();

	private DBImpl() {
		}

	public static DBImpl getInstance() {
		return instance;
	}
	
	private String redirectPage;
	private DataAccess dataAccess;
	private ResultSet resultSet;
	private String Email;
	private String command;
	private String link3;
	private String link;
	private String link2;

	public  List<Item> prepareList(String tableName, String user,
			String query) {
	

	List<String> list = new ArrayList<String>();
	ArrayList<Item> itemList = new ArrayList<Item>();
	Device device = null;
	Laptop laptop = null;
	
	if (tableName.equalsIgnoreCase("Devices")) {
		redirectPage = "Devices";
	} else if (tableName.equalsIgnoreCase("Laptops")) {
		redirectPage = "laptops";
	}
	int Id=0;
	String Name, Ip, Version, Status, Product, Password, Os;
	Name = Ip = Version = Status = Product = Password = Os = "";
	try {

		
		System.out.println("DBsearch.prepareList.query is : " + query);
		try {
			dataAccess= DataAccess.getInstance();
			resultSet = dataAccess.executeQuery(query);

		} catch (Exception e) {
			System.out.println("table " + tableName
					+ "may not be created. Creating " + tableName + e.toString());
			CreateTables.create(tableName, resultSet, query);
		}
		list.clear();

		while (resultSet.next()) {
			try {
				Id = (int) resultSet.getDouble("id");
			} catch (Exception e) {
			}
			try {
				Name = resultSet.getString("name");
			} catch (Exception e) {
			}
			try {
				Ip = resultSet.getString("ip");
			} catch (Exception e) {
			}
			try {
				Version = resultSet.getString("version");
			} catch (Exception e) {
			}
			try {
				Status = resultSet.getString("status");
			} catch (Exception e) {
			}
			try {
				Product = resultSet.getString("product");
			} catch (Exception e) {
			}
			try {
				Password = resultSet.getString("password");
			} catch (Exception e) {
			}
			try {
				Os = resultSet.getString("os");
			} catch (Exception e) {
			}
			try {
				Email = resultSet.getString("email");
			} catch (Exception e) {
			}
			String button1 = " style=\"background-color: blue; border-color: blue; color: white;  \""; // old
																										// color
																										// #3F547F;
			String button2 = " style=\"background-color: red; border-color: red; color: white;  \"";
			String button3 = " style=\"background-color: green; border-color: green; color: white;  \"";
			if (Status.equalsIgnoreCase(user)) {
				command = "release" + button1;
			} else if (Status.equalsIgnoreCase("available")) {
				command = "reserve" + button3;
			} else if (user.equalsIgnoreCase("admin")
					& !Status.equalsIgnoreCase("available")) {
				command = "release" + button2;
			} else {
				command = "reserved" + button2;
			}

			link3 = "<a href=servlet?redirectPage=" + redirectPage + "&ID="
					+ Id + "&user=" + Name + "&tableName=" + tableName
					+ "&action=resetPassword> Reset Password </a>";
			link = "<form name =\"toLaptops\" action=servlet method=post>"
					+ "<input  type=hidden name=redirectPage  value="
					+ redirectPage + ">"
					+ "<input  type=hidden name=ID  value=" + Id + ">"
					+ "<input  type=hidden name=user  value=" + user + ">"
					+ "<input  type=hidden name=tableName  value="
					+ tableName + ">"
					+ "<input  type=hidden name=action  value=" + command
					+ ">" + "<input type=submit name=submit  value="
					+ command.toUpperCase() + "></input></form>";
			if (query.equalsIgnoreCase("select email from users")) {
				list.add(Email);
			} else if (tableName.equalsIgnoreCase("Devices")) {
				if (tableName.equalsIgnoreCase("Devices")){
					device = new Device();
					device.setId(Id);
					device.setName(Name);
					device.setIp(Ip);
					device.setProduct(Product);
					device.setStatus(Status);
					device.setVersion(Version);
					itemList.add(device);
				}
				link2 = "<a href=servlet?redirectPage=" + redirectPage
						+ "&ID=" + Id + "&user=" + user + "&tableName="
						+ tableName + "&action=updateStatus>" + Version
						+ "</a>";

				if (user.equalsIgnoreCase("admin")) {
					if (!Status.equalsIgnoreCase("available")) {
						command = "release" + button2;
					} else {
						link = link2;
					}
				}

				list.add("<tr><td>" + link + "</td><td> " + Id
						+ "   </td><td> " + Name + "   </td><td> " + Ip
						+ "  </td><td>  " + link2 + "  </td><td>  "
						+ Status + "  </td><td>  " + Product
						+ "  </td></tr>");

			} else if (tableName.equalsIgnoreCase("LAPTOPS")) {
				if (tableName.equalsIgnoreCase("LAPTOPS")){
					laptop = new Laptop();
					laptop.setId(Id);
					laptop.setName(Name);
					laptop.setOs(Os);
					laptop.setPassword(Password);
					laptop.setStatus(Status);
					itemList.add(device);
				}
				link2 = "<a href=servlet?redirectPage=" + tableName
						+ "&ID=" + Id + "&user=" + user + "&tableName="
						+ tableName + "&action=updateStatus>" + Os + "</a>";
				// if(user.equalsIgnoreCase("admin")){link=link2;}
				if (user.equalsIgnoreCase("admin")) {
					if (!Status.equalsIgnoreCase("available")) {
						command = "release" + button2;
					} else {
						link = link2;
					}
				}
				list.add("<tr><td>" + link + "</td><td>  " + Id
						+ "  </td><td>  " + Name + "   </td><td>  "
						+ Password + "  </td><td>  " + link2
						+ "  </td><td>  " + Status + "</td></tr>");
			} else if (tableName.equalsIgnoreCase("USERS")) {
				list.add("<tr><td>  "
						+ Id
						+ "   </td><td>   "
						+ Name
						+ "   </td><td>   "
						+ link3
						+ "</td><td> "
						+ "<form name=adminemailchangeform action= servlet 	method=post>"
						+ " <input type=text name=email size=25 value="
						+ Email
						+ ">"
						+ "<input type=hidden name=action  value=adminemailchange>"
						+ "<input type=hidden name=username  value=" + Name
						+ ">"
						+ "<input type=hidden name=user  value=admin>"
						+ "<input type=submit name=submit  value=change>"
						+ "</form> </td></tr>");
			} else if (tableName.equalsIgnoreCase("VERSIONS")) {
				list.add("<tr><td>" + Id + "</td><td>  " + Version
						+ "   </td><td>   " + Product + "   </td> </tr>");

			}
		}

		resultSet.close();
	} catch (SQLException e) {
		System.out.println("An exception");
		e.printStackTrace();

	}
	// System.out.println(list);
	return itemList;
}

}
