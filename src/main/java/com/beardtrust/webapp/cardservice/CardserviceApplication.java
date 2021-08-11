package com.beardtrust.webapp.cardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * This class runs the card service application.
 *
 * @author Davis Hill <Davis.Hill@Smoothstack.com>
 */
@SpringBootApplication
@EnableEurekaClient
public class CardserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardserviceApplication.class, args);
	}

}
