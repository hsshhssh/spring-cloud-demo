package com.hssh.spring.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class ZookeeperDemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(ZookeeperDemoApplication.class, args);
	}
}
