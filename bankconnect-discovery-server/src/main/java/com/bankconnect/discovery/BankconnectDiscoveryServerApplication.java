package com.bankconnect.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BankconnectDiscoveryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankconnectDiscoveryServerApplication.class, args);
        System.err.println("Application Started Successfully...!");
	}
}