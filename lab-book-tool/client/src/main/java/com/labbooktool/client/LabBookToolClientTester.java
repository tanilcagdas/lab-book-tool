
package com.labbooktool.client;

import java.net.URI;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.labbooktool.model.Device;
import com.labbooktool.model.Item;
import com.labbooktool.model.Laptop;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.WebResource.Builder;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


public class LabBookToolClientTester {
	private static final String labBookPath = "labBook";
	private static String string = null;
	static Item item =null;
	

	public static void main(String[] args) {
	  
      ClientConfig clientConfig = new DefaultClientConfig();
      Client client = Client.create(clientConfig);
      WebResource service = client.resource(getBaseURI());
      
      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_XML).get(ClientResponse.class).toString());


      Builder obj = service.path("rest").path(labBookPath+"/device").accept(MediaType.APPLICATION_XML);
      List<Device> itemList =  obj.get(new GenericType<List<Device>>() {});
       for(Item it : itemList){
    	  string = it.getName();
          System.out.println(string);
      }
       
       
       item=service.path("rest").path(labBookPath+"/device/0").accept(MediaType.APPLICATION_XML).get(Device.class);
       string = item.getName();
       System.out.println(string);
       
       
       List<Laptop> laptopList = service.path("rest").path(labBookPath+"/laptop").accept(MediaType.APPLICATION_XML).get(new GenericType<List<Laptop>>() {});
       
       for(Item it : laptopList){
    	  string = it.getName();
          System.out.println(string);
            
      }
       
      
      item=service.path("rest").path(labBookPath+"/laptop/0").accept(MediaType.APPLICATION_XML).get(Laptop.class);
      string = item.getName();
      System.out.println(string);
      
       
      item = new Device();
      item.setName("testDeviceName");
      item.setId(123);
	  service.path("rest").path(labBookPath+"/device").accept(MediaType.APPLICATION_XML).put(item);
    		  
	  item = new Laptop();
      item.setName("testLaptopName");
      item.setId(456);
	  service.path("rest").path(labBookPath+"/laptop").accept(MediaType.APPLICATION_XML).put(item);
  

   }

   private static URI getBaseURI() {
      return UriBuilder.fromUri("http://localhost:8080/LabBookToolServer").build();
   }
}
