package com.labbooktool.repository;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.labbooktool.model.Device;



@Repository
public class LabBookMongoRepository {

    static final Logger logger = LoggerFactory.getLogger(LabBookMongoRepository.class);

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Device> findDevice(String deviceName) {

        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria = criteria.and("name").in(deviceName);

        query.addCriteria(criteria);
        List<Device> devices = this.mongoTemplate.find(query, Device.class);
        
        return devices;
    }

    /**
     * This will count how many Person Objects I have
     */
    public List<Device> findAllDevices() {
    	 List<Device> results = mongoTemplate.findAll(Device.class);
        
        
        return results;
    }


    public void insertDevice(Device device) {

        mongoTemplate.insert(device);
    }

	public void insertDevices(List<Device> deviceList) {
		for (Device device : deviceList) {
			try {
				mongoTemplate.insert(device);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			
		}
		
	}


}
