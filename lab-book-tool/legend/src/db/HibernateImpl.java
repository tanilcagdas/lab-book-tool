package db;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.lab.controller.LabConstants;
import com.lab.model.Item;

public class HibernateImpl {
	
	 Session session = null;
	
	private  List<Item> itemList;

	public  List<Item> select(String tableName) {
		
		try {
			

			// This step will read hibernate.cfg.xml and prepare hibernate for
			// use
			Configuration configuration = new Configuration();
			configuration.configure();
			SessionFactory sessionFactory = configuration.buildSessionFactory();
			session = sessionFactory.openSession();	 
			
			// This step will read hibernate.cfg.xml and prepare hibernate for
			// use
			if(tableName==LabConstants.DEVICES_TABLE){
				itemList=SelectDevice.select(session);
			}else if (tableName==LabConstants.LAPTOPS_TABLE){
				itemList=SelectLaptops.select(session);
			}
			session.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return itemList;
	}

	public  void release(int id,String tableName) {
		//execute("UPDATE "+tableName+" SET status='available' WHERE id="+id);
		
		// This step will read hibernate.cfg.xml and prepare hibernate for
		// use
		Configuration configuration = new Configuration();
		configuration.configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		session = sessionFactory.openSession();	 
		Transaction tran = session.beginTransaction();  
        
		

		String hql = "UPDATE "+tableName+"  set status = 'available' "  + 
		             "WHERE id = "+id;
		Query query = session.createQuery(hql);
/*		query.setParameter("command", "available");
		query.setParameter("id", id);*/
		int result = query.executeUpate();
		System.out.println("Rows affected: " + result);
		tran.commit(); 
		session.close();
		
	}

}

