package com.smashtaps.springService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringServiceApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(SpringServiceApplication.class);

	public static void main(String[] args) {
		 logger.info("Running SpringService for SmashTaps v0.0.1...");
		 
		SpringApplication.run(SpringServiceApplication.class, args);
	}

}
