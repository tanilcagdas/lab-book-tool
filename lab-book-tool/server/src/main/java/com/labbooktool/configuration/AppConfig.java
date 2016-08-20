package com.labbooktool.configuration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	static{
		System.setProperty("spring.profiles.active", "dummy");
	}

}
