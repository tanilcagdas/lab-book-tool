
package com.labbooktool.controller; 
import javax.inject.Inject;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.labbooktool.synchronize.model.RestResponseModel;
import com.labbooktool.synchronize.service.SynchronizeService;

@RestController
@RequestMapping(value = "/sync", produces = "application/json")@Component
//@Path("/sync")
public class SyncResource {
	
	@Inject
	SynchronizeService synchronizeService;
	
	@RequestMapping(value = "/fullsync", method = RequestMethod.GET)
	public RestResponseModel searchAssigne() {

		synchronizeService.fullSyncDBtoMongo();

		RestResponseModel response = RestResponseModel
				.createSuccessResponse(null);
		return response;
	}
	
	
	
}