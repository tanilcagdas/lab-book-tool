package com.labbooktool.synchronize.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labbooktool.model.Device;
import com.labbooktool.repository.LabBookMongoRepository;
import com.labbooktool.repository.LabConstants;
import com.labbooktool.server.AdminIF;
import com.labbooktool.util.DevicesConverter;

@Service
public class SynchronizeService {
	
	@Autowired
	AdminIF admin;
	
	@Autowired
	LabBookMongoRepository mongoRepository;
	
	public void fullSyncDBtoMongo(){
		List results = admin.search(LabConstants.DEVICES_TABLE);
		List<Device> devices = new ArrayList<>();
		DevicesConverter.convert(results,devices);
		mongoRepository.insertDevices(devices);
	}

}
