package com.labbooktool.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.foursquare.fongo.Fongo;
import com.labbooktool.model.Device;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class LabBookRepositoryTest {

	@Rule
    public MongoDbRule mongoDbRule = newMongoDbRule().defaultSpringMongoDb("demo-test");

	// nosql-unit requirement
	@Autowired private ApplicationContext applicationContext;
	
	@Autowired private LabBookRepository labBookRepository;


	@Test
    public void testInsertDevice(){
		List<Device> deviceList = new ArrayList<Device>();
         Device device1 = new Device();
         device1.setName("SR1");
         Device device2 = new Device();
         device2.setName("SR2");
         Device device3 = new Device();
         device3.setName("SR3");
         deviceList.add(device1);
         deviceList.add(device2);
         deviceList.add(device3);
		//         this.labBookRepository.insertDevice(device1);
//         this.labBookRepository.insertDevice(device2);
//         this.labBookRepository.insertDevice(device3);
         this.labBookRepository.insertDevices(deviceList);
		List<Device> devices = this.labBookRepository.findAllDevices();
		System.out.println(devices);
    }
	
//	@Test
//    public void testFindAllDevices(){
//         List<Device> devices = this.labBookRepository.findAllDevices();
//         
//         assertThat(devices.size()).isEqualTo(3);
//    }
//	
//	@Test
//    public void testCriteria(){
//         String deviceName = "SR1";
//		List<Device> devices = this.labBookRepository.findDevice(deviceName );
//         
//         assertThat(devices.size()).isEqualTo(1);
//    }
	
	@Configuration
	@EnableMongoRepositories
	@ComponentScan(basePackageClasses = {PersonRepository.class})  // modified to not load configs from com.johnathanmarksmith.mongodb.example.MongoConfiguration
	@PropertySource("classpath:application.properties")
	static class PersonRepositoryTestConfiguration extends AbstractMongoConfiguration {

	    @Override
	    protected String getDatabaseName() {
	        return "demo-test";
	    }
		
	    @Override
		public Mongo mongo() {
	    	// uses fongo for in-memory tests
			return new Fongo("mongo-test").getMongo();
		}
		
	    @Override
	    protected String getMappingBasePackage() {
	        return "com.labbooktool.domain";
	    }

	}
}
