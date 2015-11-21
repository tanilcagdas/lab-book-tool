
package com.labbooktool.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class HelloWorldClient {
   public static void main(String[] args) {
      ClientConfig clientConfig = new DefaultClientConfig();
      Client client = Client.create(clientConfig);
      WebResource service = client.resource(getBaseURI());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_XML).get(ClientResponse.class).toString());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.APPLICATION_XML).get(String.class).toString());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_HTML).get(String.class).toString());
      
      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_HTML).get(ClientResponse.class).toString());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.APPLICATION_XML).get(String.class).toString());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_XML).get(String.class).toString());

      System.out.println(service.path("rest").path("helloworld").accept(MediaType.TEXT_XML).post(String.class).toString());
   }

   private static URI getBaseURI() {
      return UriBuilder.fromUri("http://localhost:8080/LabBookToolServer").build();
   }
}
