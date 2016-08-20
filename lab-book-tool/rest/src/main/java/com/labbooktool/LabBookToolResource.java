package com.labbooktool;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.labbooktool.model.Device;
import com.labbooktool.model.DeviceListServiceBean;
import com.labbooktool.model.Laptop;
import com.labbooktool.model.LaptopListServiceBean;
import com.labbooktool.repository.LabConstants;
import com.labbooktool.server.AdminIF;
import com.labbooktool.server.ItemServiceDummyImpl;

@Component
@Path("/labBook")
public class LabBookToolResource {
	
//	@Inject
//	@Named("ItemServiceDummyImpl")
	AdminIF admin;
	
	@PostConstruct
	public void init(){
//		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		admin = (ItemServiceDummyImpl) ApplicationContextProvider.getApplicationContext().getBean("ItemServiceDummyImpl");
	}
	
	public LabBookToolResource() {
		System.setProperty("spring.profiles.active", "dummy");
	}
	

	@GET
    @Path("/device")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public DeviceListServiceBean queryDevices(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size,
            @QueryParam("name") String deviceName,@Context HttpServletRequest req, @HeaderParam(value = "username") String username) throws ClassNotFoundException{
		List<Device> itemList = (List<Device>) admin.search(LabConstants.DEVICES_TABLE);
		DeviceListServiceBean deviceListServiceBean = new DeviceListServiceBean();
		deviceListServiceBean.setDeviceList(itemList);
		return deviceListServiceBean ;
	}
	@GET
	@Path("/laptop")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public LaptopListServiceBean queryLaptops(@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size,
			@QueryParam("name") String deviceName){
		List<Laptop> itemList = (List<Laptop>) admin.search(LabConstants.LAPTOPS_TABLE);

		LaptopListServiceBean laptopListServiceBean = new LaptopListServiceBean();
		laptopListServiceBean.setLaptopList(itemList);
		return laptopListServiceBean ;
	}
	
	@GET
    @Path("/reservedevice")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public DeviceListServiceBean reserveDevice(@DefaultValue("0") @QueryParam("start") int start,
            @DefaultValue("10") @QueryParam("size") int size,
            @QueryParam("name") String deviceName,@Context HttpServletRequest req, @HeaderParam(value = "username") String username) throws ClassNotFoundException{
		List<Device> itemList = (List<Device>) admin.search(LabConstants.DEVICES_TABLE);
		DeviceListServiceBean deviceListServiceBean = new DeviceListServiceBean();
		deviceListServiceBean.setDeviceList(itemList);
		String user = req.getRemoteUser();
		admin.reserve(username, deviceListServiceBean.getDeviceList().get(0).getId(), LabConstants.DEVICES_TABLE);
		return deviceListServiceBean ;
	}

	@GET
	@Path("/releasedevice")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public DeviceListServiceBean releaseDevice(@DefaultValue("0") @QueryParam("start") int start,
			@DefaultValue("10") @QueryParam("size") int size,
			@QueryParam("name") String deviceName,@Context HttpServletRequest req, @HeaderParam(value = "username") String username) throws ClassNotFoundException{
		List<Device> itemList = (List<Device>) admin.search(LabConstants.DEVICES_TABLE);
		DeviceListServiceBean deviceListServiceBean = new DeviceListServiceBean();
		deviceListServiceBean.setDeviceList(itemList);
		String user = req.getRemoteUser();
		admin.release(username, deviceListServiceBean.getDeviceList().get(0).getId(), LabConstants.DEVICES_TABLE);
		return deviceListServiceBean ;
	}
	
	@PUT
	@Path("/device")
	@Consumes({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public void addDevices(DeviceListServiceBean deviceListServiceBean){
		System.out.println(deviceListServiceBean);
		admin.add(deviceListServiceBean.getDeviceList());
	}
	
}