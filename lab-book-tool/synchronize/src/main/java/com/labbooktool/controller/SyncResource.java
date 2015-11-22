
package com.labbooktool.controller;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.labbooktool.synchronize.model.RestResponseModel;
import com.labbooktool.synchronize.service.SynchronizeService;
import com.labbooktool.util.ApplicationContextProvider;

@Component
@Path("/sync")
public class SyncResource {

	SynchronizeService synchronizeService;

	@PostConstruct
	public void init() {
		synchronizeService = (SynchronizeService) ApplicationContextProvider.getApplicationContext()
				.getBean(SynchronizeService.class);
	}

	@GET
	@Path("/fullsync")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public RestResponseModel searchAssigne() {

		synchronizeService.fullSyncDBtoMongo();

		RestResponseModel response = RestResponseModel.createSuccessResponse(null);
		return response;
	}

}