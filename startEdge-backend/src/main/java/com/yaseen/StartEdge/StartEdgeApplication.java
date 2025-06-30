package com.yaseen.StartEdge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StartEdgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartEdgeApplication.class, args);
	}

}
