package rest;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.junit.Test;

import com.labbooktool.model.Device;
import com.labbooktool.model.DeviceListServiceBean;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
import com.sun.istack.logging.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LabBookToolResourceTester {
	
	
	private static final String labBookPath = "labBook";
	private static final String SERVER = "rest";
	private static String string = null;
	static Item item = null;
	static ClientConfig clientConfig = new DefaultClientConfig();
	static Client client = Client.create(clientConfig);
	static WebResource service = client.resource(getBaseURI());
	private String pathString = "webresources";

	@Test
	public void test() {
		
		List<Device> result = getDevices();
		System.out.println(result);
//		addTestDevices();
		
//		fail("Not yet implemented");
	}
	
	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/"+SERVER).build();
	}
	
	public void addTestDevices(){
		
		Builder obj = service.path(pathString ).path(labBookPath + "/device").accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
		DeviceListServiceBean deviceListServiceBean = new DeviceListServiceBean();
		List<Device> deviceList  ;
		deviceList = new ArrayList<Device>();
		Device d1 = new Device();
		Device d2 = new Device();
		Device d3 = new Device();
		d1.setId(1000+((int)(Math.random()*100)));
		d2.setId(2000+((int)(Math.random()*100)));
		d3.setId(3000+((int)(Math.random()*100)));
		d1.setName("testDevice1");
		d2.setName("testDevice2");
		d3.setName("testDevice3");
		deviceList.add(d1);
		deviceList.add(d2);
		deviceList.add(d3);
		deviceListServiceBean.setDeviceList(deviceList);
		obj.put(deviceListServiceBean );
	}

	public List<Device> getDevices() {
		try {

			Builder obj = service.path(pathString ).path(labBookPath + "/device").accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
			DeviceListServiceBean result = obj.get(new GenericType<DeviceListServiceBean>() {
			});
			List<Device> itemList = result.getDeviceList();
			for (Item it : itemList) {
				string = it.getName();
				System.out.println(string);
			}
			return itemList;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).log(Level.SEVERE, "LabBookToolClient.getDevices() exception occured", e);
		}
		return null;
	}

	public List<Laptop> getLaptops() {
		try {
			List<Laptop> laptopList = service.path("rest").path(labBookPath + "/laptop").accept(MediaType.APPLICATION_XML).get(new GenericType<List<Laptop>>() {
			});

			for (Item it : laptopList) {
				string = it.getName();
				System.out.println(string);

			}
			return laptopList;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).log(Level.SEVERE, "LabBookToolClient.getDevices() exception occured", e);
		}
		return null;
	}

	public void reserve(String selectorUser, int selectedID, String selectedTable) {
		// TODO Auto-generated method stub

	}

	public void release(String selectorUser, int selectedID, String selectedTable) {
		// TODO Auto-generated method stub

	}
	

}
