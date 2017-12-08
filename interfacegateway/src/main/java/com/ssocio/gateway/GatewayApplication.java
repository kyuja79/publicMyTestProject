package com.ssocio.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.ssocio.gateway.filters.PreFilter;

import de.codecentric.boot.admin.config.EnableAdminServer;

@EnableCaching
@EnableZuulProxy
@EnableAdminServer
@SpringBootApplication
public class GatewayApplication {
	
	  public static void main(String[] args) {
		    SpringApplication.run(GatewayApplication.class, args);
	  }
	  
}
