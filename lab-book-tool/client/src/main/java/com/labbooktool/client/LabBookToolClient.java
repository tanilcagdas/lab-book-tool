package com.labbooktool.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.labbooktool.controller.IndexBean;
import com.labbooktool.model.Device;
import com.labbooktool.model.DeviceListServiceBean;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
import com.labbooktool.model.LaptopListServiceBean;
import com.sun.istack.logging.Logger;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LabBookToolClient {
	private static final String labBookPath = "labBook";
	private static final String SERVER = "rest";
	private static String string = null;
	static Item item = null;
	static ClientConfig clientConfig = new DefaultClientConfig();
	static Client client = Client.create(clientConfig);
	static WebResource service = client.resource(getBaseURI());
	private String pathString = "webresources";

	private static URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/"+SERVER).build();
	}

	public List<Device> getDevices() {
		try {

			Builder obj = service.path(pathString ).path(labBookPath + "/device").accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
			IndexBean indexBean = FacesContext.getCurrentInstance().getApplication().evaluateExpressionGet(FacesContext.getCurrentInstance(), "#{indexBean}", IndexBean.class);
			Object username = indexBean.getLoginUserName();
			obj.header("username", username);
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
			
			
			Builder obj = service.path(pathString ).path(labBookPath + "/laptop").accept(MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON);
			LaptopListServiceBean result = obj.get(new GenericType<LaptopListServiceBean>() {
			});

			List<Laptop> laptopList = result.getLaptopList();
			for (Item it : laptopList ) {
				string = it.getName();
				System.out.println(string);

			}
			return laptopList;
		} catch (Exception e) {
			Logger.getLogger(this.getClass()).log(Level.SEVERE, "LabBookToolClient.getDevices() exception occured", e);
		}
		return new ArrayList<Laptop>();
	}

	public void reserve(String selectorUser, int selectedID, String selectedTable) {
		// TODO Auto-generated method stub

	}

	public void release(String selectorUser, int selectedID, String selectedTable) {
		// TODO Auto-generated method stub

	}
}
